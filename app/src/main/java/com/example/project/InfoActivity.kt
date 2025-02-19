package com.example.project

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info) // Face legatura cu activity_info.xml

        val serviceIntent = Intent(this, MyBackgroundService::class.java) // initializeaza un intent care se ocupa de MyBackgroundService
        val foregroundServiceIntent = Intent(this, MyForegroundService::class.java) // initializeaza un intent care se ocupa de MyForegroundService
        // Start the background service
        val startServiceButton = findViewById<Button>(R.id.start_service_button) // Butonul initializeaza serviciul in background
        startServiceButton.setOnClickListener { // Functie care se apeleaza cand se apasa pe buton
            startService(serviceIntent)
        }

        // Stop the background service
        val stopServiceButton = findViewById<Button>(R.id.stop_service_button) // Butonul opreste serviciul in background
        stopServiceButton.setOnClickListener { // Functie care se apeleaza cand se apasa pe buton
            stopService(serviceIntent)
        }

        // Start the foreground service
        val startForegroundServiceButton = findViewById<Button>(R.id.play_music_button) // Butonul initializeaza serviciul foreground
        startForegroundServiceButton.setOnClickListener { // Functie care se apeleaza cand se apasa pe buton
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Verifica daca versiunea Android este mai mare sau egala cu Oreo
                startForegroundService(foregroundServiceIntent) // Se porneste serviciul
            } else {
                startService(foregroundServiceIntent)
            }
        }

        // Stop the foreground service
        val stopForegroundServiceButton = findViewById<Button>(R.id.stop_music_button) // Butonul opreste serviciul foreground
        stopForegroundServiceButton.setOnClickListener { // Functie care se apeleaza cand se apasa pe buton
            stopService(foregroundServiceIntent) // Se opreste serviciul
        }


    }
}