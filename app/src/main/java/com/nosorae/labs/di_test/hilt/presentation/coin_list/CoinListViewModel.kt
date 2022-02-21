package com.nosorae.labs.di_test.hilt.presentation.coin_list

import android.util.Log
import androidx.lifecycle.*
import com.nosorae.labs.di_test.hilt.common.Constant.PARAM_COIN_ID
import com.nosorae.labs.di_test.hilt.common.Resource
import com.nosorae.labs.di_test.hilt.domain.use_case.get_coin.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<CoinListState>(CoinListState.UnInitialized)
    val state: LiveData<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    Log.d("asdf", "${result.data}")
                    result.data?.let { list ->
                        _state.value =  CoinListState.Success(list)
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