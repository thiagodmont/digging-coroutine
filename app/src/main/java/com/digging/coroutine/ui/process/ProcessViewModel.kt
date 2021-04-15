package com.digging.coroutine.ui.process

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.digging.coroutine.base.BaseViewModel
import com.digging.coroutine.base.SingleLiveEvent
import com.digging.coroutine.base.sendEvent
import com.digging.coroutine.extensions.toLiveData
import kotlinx.coroutines.*

class ProcessViewModel : BaseViewModel() {

    private val _loading = MutableLiveData(false)
    val loading = _loading.toLiveData()

    private val _toast = SingleLiveEvent<String>()
    val toast = _toast.toLiveData()

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    fun startProcess() {
        viewModelScope.launch(job) {
            runCatching {
                _loading.value = true

                val result = doLongRunningTask()
                _toast.sendEvent(result)

                _loading.value = false
            }.recoverCatching {
                _toast.sendEvent("Ops! We have some problems!")
                _loading.value = false
            }
        }
    }

    fun cancelProcess() {
        job.cancel()
    }

    private suspend fun doLongRunningTask(): String =
        withContext(Dispatchers.Default) {
            // Added delay to simulate a long running task;
            delay(5000)
            "Task is done!"
        }
}