package com.lkw1120.pokedex.ui.detail

import com.lkw1120.pokedex.usecase.model.PokeDetail

sealed interface PokeDetailScreenState {

    data object Loading : PokeDetailScreenState

    data class Success(
        val pokeDetail: PokeDetail,
        val isSaved: Boolean,
    ) : PokeDetailScreenState

    data class Error(
        val errorMessage: String?
    ) : PokeDetailScreenState
}