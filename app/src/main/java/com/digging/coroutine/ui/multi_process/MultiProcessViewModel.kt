package com.digging.coroutine.ui.multi_process

import androidx.lifecycle.MutableLiveData
import com.digging.coroutine.base.BaseViewModel
import com.digging.coroutine.extensions.toLiveData
import kotlinx.coroutines.*

class MultiProcessViewModel: BaseViewModel() {

    private val _loadingJobOne = MutableLiveData(false)
    val loadingJobOne = _loadingJobOne.toLiveData()

    private val _loadingJobTwo = MutableLiveData(false)
    val loadingJobTwo = _loadingJobTwo.toLiveData()

    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private var jobOne: Job? = null
    private var jobTwo: Job? = null

    fun startMultiProcess() {
        _loadingJobOne.value = true
        _loadingJobTwo.value = true

        jobOne = scope.launch {
            runCatching {
                repeat(10) {
                    println("count: $it")
                    delay(500)
                }

                // you need to use postValue because we are executing out of main thread;
                _loadingJobOne.postValue(false)
            }.recoverCatching {
                // you need to use postValue because we are executing out of main thread;
                _loadingJobOne.postValue(false)
            }
        }

        jobTwo = scope.launch {
            runCatching {
                repeat(15) {
                    println("count: $it")
                    delay(500)
                }

                // you need to use postValue because we are executing out of main thread;
                _loadingJobTwo.postValue(false)
            }.recoverCatching {
                // you need to use postValue because we are executing out of main thread;
                _loadingJobTwo.postValue(false)
            }
        }
    }

    fun cancelJobOne() {
        jobOne?.cancel()
    }

    fun cancelJobTwo() {
        jobTwo?.cancel()
    }

    fun cancelAllScope() {
        scope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllScope()
    }
}