package com.nosorae.labs.clean_architecture.hilt.presentation.coin_list

import android.util.Log
import androidx.lifecycle.*
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
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


    init {
        getCoins()
        timerWorkerChainTest()
    }

    private fun timerWorkerChainTest() {
        workManager
            .beginWith(
                OneTimeWorkRequestBuilder<TestWorker>()
                    .setInputData(createDataByStringTest("1"))
                    .build()
            )
            .then(OneTimeWorkRequest.from(TestWorker::class.java))
            .then(OneTimeWorkRequest.from(TestWorker::class.java))
            .enqueue() // 실질적인 work 의 시작은 enqueue
    }

    private fun createDataByStringTest(input: String) =
        Data
            .Builder()
            .putString(KEY_TEST_WORKER_DATA, input)
            .build()


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