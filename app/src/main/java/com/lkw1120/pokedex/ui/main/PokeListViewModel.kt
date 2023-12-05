package com.lkw1120.pokedex.ui.main

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lkw1120.pokedex.ui.base.BaseViewModel
import com.lkw1120.pokedex.usecase.PokedexUseCase
import com.lkw1120.pokedex.usecase.model.PokeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor (
    private val pokedexUseCase: PokedexUseCase
): BaseViewModel() {

    val pokeList: Flow<PagingData<PokeItem>> =
        pokedexUseCase.getPokeListFromRemote().cachedIn(workerScope)


    private val _pokeListScreenState: MutableStateFlow<PokeListScreenState> =
        MutableStateFlow(PokeListScreenState.Loading)
    val pokeListScreenState: StateFlow<PokeListScreenState> =
        _pokeListScreenState.asStateFlow()

    fun success() = workerScope.launch(Dispatchers.IO) {
        _pokeListScreenState.value = PokeListScreenState.Success
    }

    fun refresh() = workerScope.launch(Dispatchers.IO) {
        _pokeListScreenState.value = PokeListScreenState.Loading
    }

    fun error(message: String) = workerScope.launch(Dispatchers.IO) {
        _pokeListScreenState.value = PokeListScreenState.Error(
            errorMessage = message
        )
    }

    override fun exceptionHandler(throwable: Throwable) {
        super.exceptionHandler(throwable)
        error(throwable.stackTraceToString())
    }

}