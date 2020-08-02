package com.phantomLabs.simpleClock.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phantomLabs.simpleClock.R

class TimerFragment : Fragment() {

    private lateinit var timerViewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timerViewModel =
            ViewModelProviders.of(this).get(TimerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        timerViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}