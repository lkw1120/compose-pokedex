package com.lkw1120.pokedex.feature.main

sealed interface CatchListScreenState {

    data object Loading : CatchListScreenState

    data object Success : CatchListScreenState

    data class Error(
        val errorMessage: String?
    ) : CatchListScreenState
}