package com.sadapay.assignment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(){
    // Coroutine related properties for managing background tasks
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // LiveData for handling loading and error states
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    // Function to handle exceptions and update error LiveData
    private fun handleException(exception: Throwable) {
        _error.value = exception
    }

    // Function to start a background task
    protected fun launchDataLoad(block: suspend () -> Unit) {
        _isLoading.value = true
        viewModelScope.launchCatchingExceptions {
            block()
        }.invokeOnCompletion {
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel() // Cancel all coroutines when the ViewModel is cleared
    }

    // Helper function to launch a coroutine and catch exceptions
    private inline fun CoroutineScope.launchCatchingExceptions(
        crossinline block: suspend () -> Unit
    ): Job {
        return launch {
            try {
                block()
            } catch (e: Throwable) {
                handleException(e)
            }
        }
    }
}
