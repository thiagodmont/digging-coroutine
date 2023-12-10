package com.digging.coroutine.ui.race

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.digging.coroutine.R
import com.digging.coroutine.base.BaseFragment
import com.digging.coroutine.base.viewBinding
import com.digging.coroutine.databinding.FragmentRaceBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RaceFragment: BaseFragment(R.layout.fragment_race) {
    private val viewBind by viewBinding(FragmentRaceBinding::bind)

    private var raceEnd = false
    private var greenJob: Job? = null
    private var redJob: Job? = null
    private var blueJob: Job? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setupView(savedInstanceState: Bundle?) {
        viewBind.buttonStart.setOnClickListener {
            startUpdate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        resetRun()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startUpdate() {
        resetRun()

        greenJob = MainScope().launch() {
            println("greenJob start : ${Thread.currentThread().name}")
            startRunning(viewBind.progressBarGreen)
            println("greenJob end : ${Thread.currentThread().name}")
        }

        redJob = MainScope().launch {
            println("redJob start : ${Thread.currentThread().name}")
            startRunning(viewBind.progressBarRed)
            println("redJob end : ${Thread.currentThread().name}")
        }

        blueJob = MainScope().launch() {
            println("blueJob start : ${Thread.currentThread().name}")
            startRunning(viewBind.progressBarBlue)
            println("blueJob end : ${Thread.currentThread().name}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun startRunning(progressBar: RoundCornerProgressBar) {
        progressBar.progress = 0f
        while (progressBar.progress < 1000 && !raceEnd) {
            progressBar.progress += (1..10).random()
            delay(10)
        }
        if (!raceEnd) {
            raceEnd = true
            Toast.makeText(progressBar.context, "${progressBar.tooltipText} won!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetRun() {
        raceEnd = false
        greenJob?.cancel()
        blueJob?.cancel()
        redJob?.cancel()
    }
}