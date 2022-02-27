package com.nosorae.labs.clean_architecture.hilt.presentation.coin_list

import android.util.Log
import androidx.lifecycle.*
import androidx.work.*
import com.nosorae.labs.clean_architecture.hilt.background.work_manager.work.TestWorker
import com.nosorae.labs.clean_architecture.hilt.common.Constants.KEY_TEST_WORKER_DATA
import com.nosorae.labs.clean_architecture.hilt.common.Resource
import com.nosorae.labs.clean_architecture.hilt.domain.use_case.get_coin.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val workManager: WorkManager,
) : ViewModel() {

    private val _state = MutableLiveData<CoinListState>(CoinListState.UnInitialized)
    val state: LiveData<CoinListState> = _state

    internal val workInfosByTag: LiveData<List<WorkInfo>>
    internal val workInfosByChainName: LiveData<List<WorkInfo>>

    companion object {
        const val WORK_CHAIN_NAME = "work_chain_name"
        const val WORK_INFO_BY_TAG = "workInfo by tag"
    }

    init {
        getCoins()
        //timerWorkerChainTest()
        workInfosByChainName = workManager.getWorkInfosForUniqueWorkLiveData(WORK_CHAIN_NAME)
        workInfosByTag = workManager.getWorkInfosByTagLiveData(WORK_INFO_BY_TAG)
    }

    fun timerWorkerChainTest() {
        workManager
            .beginUniqueWork(
                WORK_CHAIN_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<TestWorker>()
                    .setInputData(createDataByStringTest("1"))
                    .build()
            )
            .then(
                OneTimeWorkRequestBuilder<TestWorker>()
                    .addTag(WORK_INFO_BY_TAG)
                    .build()
            )
            .then(
                OneTimeWorkRequestBuilder<TestWorker>()
                    .build()
            ) // 반복문으로 쓸 수도 있음! 보여주기식이라 비효율적이게 보일 수 있다.
            .enqueue() // 실질적인 work 의 시작은 enqueue
    }

    private fun createDataByStringTest(input: String) =
        Data
            .Builder()
            .putString(KEY_TEST_WORKER_DATA, input)
            .build()

    fun cancelWorkRequest() {
        workManager.cancelUniqueWork(WORK_CHAIN_NAME)
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("asdf", "${result.data}")
                    result.data?.let { list ->
                        _state.value = CoinListState.Success(list)
                    }
                }
                is Resource.Loading -> {
                    Log.d("asdf", "loading")
                    _state.value = CoinListState.Loading
                }
                is Resource.Error -> {
                    Log.d("asdf", "${result.message}")
                    _state.value = CoinListState.Error(result.message ?: "예상치 못한 에러 발생")
                }
            }
        }.launchIn(viewModelScope) // flows are asynchronous so we launch it in the ViewModel scope
    }

}