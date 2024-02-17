package com.lkw1120.pokedex.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import timber.log.Timber

open class BaseViewModel: ViewModel() {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            exceptionHandler(throwable)
        }

    protected val workerScope =
        viewModelScope + coroutineExceptionHandler

    open fun exceptionHandler(throwable: Throwable) {
        Timber.tag("error").d(throwable.stackTraceToString())
    }

}