package com.lkw1120.pokedex.feature.detail

import com.lkw1120.pokedex.core.base.BaseViewModel
import com.lkw1120.pokedex.core.data.PokedexUseCase
import com.lkw1120.pokedex.core.model.PokeDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    private val pokedexUseCase: PokedexUseCase
): BaseViewModel() {

    private val _pokeDetailScreenState: MutableStateFlow<PokeDetailScreenState> =
        MutableStateFlow(PokeDetailScreenState.Loading)
    val pokeDetailScreenState: StateFlow<PokeDetailScreenState> =
        _pokeDetailScreenState.asStateFlow()


    fun getPokeDetail(name: String) = workerScope.launch(Dispatchers.IO) {
        pokedexUseCase.getPokeDetailFromLocal(name).collect {
            it?.let { data ->
                _pokeDetailScreenState.value = PokeDetailScreenState.Success(
                    pokeDetail = data,
                    isSaved = true
                )
            }?:pokedexUseCase.getPokeDetailFromRemote(name).collect { data ->
                _pokeDetailScreenState.value = PokeDetailScreenState.Success(
                    pokeDetail = data,
                    isSaved = false
                )
            }
        }
    }

    fun refresh() = workerScope.launch(Dispatchers.IO) {
        _pokeDetailScreenState.value = PokeDetailScreenState.Loading
    }

    fun error(message: String) = workerScope.launch(Dispatchers.IO) {
        _pokeDetailScreenState.value = PokeDetailScreenState.Error(
            errorMessage = message
        )
    }

    override fun exceptionHandler(throwable: Throwable) {
        super.exceptionHandler(throwable)
        error(throwable.stackTraceToString())
    }

    fun updatePokeDetail(data: PokeDetail) = workerScope.launch(Dispatchers.IO) {
        pokedexUseCase.updatePokeDetail(data)
        getPokeDetail(data.name)
    }

    fun deletePokeDetail(data: PokeDetail) = workerScope.launch(Dispatchers.IO) {
        pokedexUseCase.deletePokeDetail(data)
        getPokeDetail(data.name)
    }

}