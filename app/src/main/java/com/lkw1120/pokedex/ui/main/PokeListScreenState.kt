package com.lkw1120.pokedex.ui.main

sealed interface PokeListScreenState {

    data object Loading : PokeListScreenState

    data object Success : PokeListScreenState

    data class Error(
        val errorMessage: String?
    ) : PokeListScreenState
}