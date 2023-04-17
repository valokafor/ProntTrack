package com.valokafor.prontotrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.valokafor.SimpleStepCounterApp
import com.valokafor.prontotrack.ui.theme.ProntoTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: TrackerViewModel by viewModels()
        setContent {
            ProntoTrackTheme {
                SimpleStepCounterApp(viewModel)
            }
        }
    }
}