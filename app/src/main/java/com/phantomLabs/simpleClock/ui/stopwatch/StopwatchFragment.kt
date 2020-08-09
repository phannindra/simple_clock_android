package com.phantomLabs.simpleClock.ui.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.phantomLabs.simpleClock.R

class StopwatchFragment : Fragment() {

    private lateinit var root:View
    private var isRunning:Boolean = false
    private lateinit var chronometer: Chronometer
    private lateinit var startButton: Button
    private lateinit var lapTimeTextView:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        chronometer = root.findViewById(R.id.chronometer)
        startButton = root.findViewById(R.id.btn_start)
        lapTimeTextView = root.findViewById(R.id.txt_lap)
        val lapButton = root.findViewById<Button>(R.id.btn_lapTime)
        val resetButton = root.findViewById<Button>(R.id.btn_reset)

        lapTimeTextView.text = ""
        chronometer.format = "%s:%s%s:%s%s"
        chronometer.text = "00:00:00"
        if (savedInstanceState != null) {
            isRunning = savedInstanceState.getBoolean("running")
        }


        startButton.setOnClickListener {
            if (!isRunning){
                isRunning = true
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                startButton.text = "Stop"

            }else{
                isRunning = false
                chronometer.stop()
                startButton.text = "Start"
            }
        }

        lapButton.setOnClickListener {
            if(isRunning)
                showLapTime()
        }

        resetButton.setOnClickListener {
            resetChronometer()
        }

        return root
    }

    private fun showLapTime(){
        val text = lapTimeTextView.text.toString()
        val timeElapsed = (SystemClock.elapsedRealtime() - chronometer.base)/1000
        val seconds = timeElapsed%60
        val minutes = timeElapsed/60

        lapTimeTextView.text = "$text\n $minutes:$seconds"

    }

    private fun resetChronometer(){
        isRunning = false
        chronometer.stop()
        startButton.text = "start"
        chronometer.text = "00:00:00"
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean("running", isRunning)
    }


}