package com.phantomLabs.simpleClock.ui.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.phantomLabs.simpleClock.R

class TimerFragment : Fragment() {

    lateinit var root:View
    private var timerLimit:Long = 0
    var isRunning = false
    lateinit var secondsPicker:NumberPicker
    lateinit var minutesPicker:NumberPicker
    lateinit var hoursPicker:NumberPicker
    lateinit var startButton:Button
    lateinit var timer:CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_timer, container, false)

        startButton = root.findViewById(R.id.button)
        secondsPicker = root.findViewById(R.id.np_seconds)
        secondsPicker.minValue = 0
        secondsPicker.maxValue = 59

        minutesPicker = root.findViewById(R.id.np_minutes)
        minutesPicker.minValue = 0
        minutesPicker.maxValue = 59

        hoursPicker = root.findViewById(R.id.np_hours)
        hoursPicker.minValue = 0
        hoursPicker.maxValue = 60


        startButton.setOnClickListener {
            if(!isRunning){
                isRunning = true
                timerLimit = ((
                        (hoursPicker.value*3600) + (minutesPicker.value*60) + (secondsPicker.value)
                        )*1000).toLong()
                showCountDown()
                startButton.text = "Reset"
            }else{
                isRunning = false
                resetTimer()
                startButton.text = "Start"
            }
        }
        return root
    }

    private fun resetTimer(){
        timer.cancel()
        secondsPicker.value = 0
        minutesPicker.value = 0
        hoursPicker.value = 0
    }

    private fun showCountDown() {

        timer = object : CountDownTimer(timerLimit, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                if (isAdded) {
                    secondsPicker.value =  (millisUntilFinished / 1000).toInt()
                    minutesPicker.value = (millisUntilFinished / 60000).toInt()
                    hoursPicker.value = (millisUntilFinished / 3600000).toInt()
                }
            }

            override fun onFinish() {
                if (isAdded) {
                    isRunning = false
                    startButton.text = "Start"
                }
            }
        }.start()

    }
}