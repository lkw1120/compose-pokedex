package com.lkw1120.pokedex.ui.main

sealed interface CatchListScreenState {

    data object Loading : CatchListScreenState

    data object Success : CatchListScreenState

    data class Error(
        val errorMessage: String?
    ) : CatchListScreenState
}