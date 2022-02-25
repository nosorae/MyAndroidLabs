package com.nosorae.labs.clean_architecture.hilt.presentation.coin_detail

import android.util.Log
import androidx.lifecycle.*
import com.nosorae.labs.clean_architecture.hilt.common.Constants.PARAM_COIN_ID
import com.nosorae.labs.clean_architecture.hilt.common.Resource
import com.nosorae.labs.clean_architecture.hilt.domain.use_case.get_coins.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableLiveData(CoinDetailState())
    val state: LiveData<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            Log.e(PARAM_COIN_ID, coinId)
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Loading -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope) // flows are asynchronous so we launch it in the ViewModel scope
    }

}