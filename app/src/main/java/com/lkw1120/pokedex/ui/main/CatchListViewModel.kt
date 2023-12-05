package com.lkw1120.pokedex.ui.main

import com.lkw1120.pokedex.ui.base.BaseViewModel
import com.lkw1120.pokedex.usecase.PokedexUseCase
import com.lkw1120.pokedex.usecase.model.PokeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchListViewModel @Inject constructor (
    private val pokedexUseCase: PokedexUseCase
): BaseViewModel() {

    val pokeList: StateFlow<List<PokeItem>> =
        pokedexUseCase.getPokeListFromLocal().stateIn(
            scope = workerScope,
            initialValue = listOf(),
            started = SharingStarted.WhileSubscribed(5000L)
        )

    private val _catchListScreenState: MutableStateFlow<CatchListScreenState> =
        MutableStateFlow(CatchListScreenState.Loading)
    val catchListScreenState: StateFlow<CatchListScreenState> =
        _catchListScreenState.asStateFlow()


    fun success() = workerScope.launch(Dispatchers.IO) {
        _catchListScreenState.value = CatchListScreenState.Success
    }

    fun refresh() = workerScope.launch(Dispatchers.IO) {
        _catchListScreenState.value = CatchListScreenState.Loading
    }

    fun error(message: String) = workerScope.launch(Dispatchers.IO) {
        _catchListScreenState.value = CatchListScreenState.Error(
            errorMessage = message
        )
    }


    override fun exceptionHandler(throwable: Throwable) {
        super.exceptionHandler(throwable)
        error(throwable.stackTraceToString())
    }

}