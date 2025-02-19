package com.example.project

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyBackgroundService : Service() { // Clasa care se ocupa de serviciul din background
    private var mediaPlayer: MediaPlayer? = null // Media player pentru a reda muzica

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { // Functie care porneste serviciul
        // Start playing the song
        mediaPlayer = MediaPlayer.create(this, R.raw.song) // Se creeaza un media player care reda muzica
        mediaPlayer?.start()
        Toast.makeText(this, "You are poor and broke my friend!(BGS)", Toast.LENGTH_SHORT).show() // Se afiseaza un mesaj
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop playing the song
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show() // Se afiseaza un mesaj
    }

    override fun onBind(intent: Intent?): IBinder? { // Functie care se apeleaza cand se face legatura cu serviciul
        return null
    }
}