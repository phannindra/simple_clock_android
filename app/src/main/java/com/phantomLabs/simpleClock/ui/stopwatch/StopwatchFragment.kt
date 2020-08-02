package com.phantomLabs.simpleClock.ui.stopwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phantomLabs.simpleClock.R

class StopwatchFragment : Fragment() {

    private lateinit var stopwatchViewModel: StopwatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stopwatchViewModel =
            ViewModelProviders.of(this).get(StopwatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        stopwatchViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}