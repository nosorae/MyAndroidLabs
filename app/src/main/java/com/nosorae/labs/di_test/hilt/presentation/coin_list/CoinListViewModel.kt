package com.nosorae.labs.di_test.hilt.presentation.coin_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nosorae.labs.di_test.hilt.common.Resource
import com.nosorae.labs.di_test.hilt.domain.use_case.get_coin.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class CoinListViewModel @ViewModelInject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
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
                    result.data?.let { list ->
                        _state.value =  CoinListState.Success(list)
                    }
                }
                is Resource.Loading -> {
                    _state.value = CoinListState.Loading
                }
                is Resource.Error -> {
                    _state.value = CoinListState.Error(result.message ?: "예상치 못한 에러 발생")
                }
            }
        }.launchIn(viewModelScope) // flows are asynchronous so we launch it in the ViewModel scope
    }

}