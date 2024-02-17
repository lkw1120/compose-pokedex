package com.lkw1120.pokedex.feature.detail

import com.lkw1120.pokedex.core.model.PokeDetail


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