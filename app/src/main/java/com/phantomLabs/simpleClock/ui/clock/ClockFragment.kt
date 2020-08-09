package com.phantomLabs.simpleClock.ui.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.phantomLabs.simpleClock.R
import java.text.SimpleDateFormat
import java.util.*

class ClockFragment : Fragment() {

    private lateinit var clockViewModel: ClockViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        clockViewModel =
            ViewModelProviders.of(this).get(ClockViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textDate: TextView = root.findViewById(R.id.txt_date)
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")

        textDate.text = simpleDateFormat.format(Date())

        return root
    }
}