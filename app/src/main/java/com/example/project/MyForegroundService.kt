package com.example.project

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    private lateinit var mediaPlayer1: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer1 = MediaPlayer.create(this, R.raw.song1) // Se creeaza un media player care reda muzica
        mediaPlayer1.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { // Functie care porneste serviciul foreground
        createNotificationChannel()
        val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentTitle("Foreground Service") // Se seteaza titlul notificarii
            .setContentText("Playing music") // Se seteaza textul notificarii
            .setSmallIcon(R.drawable.ic_music_note) // Se seteaza iconita notificarii
            .build()

        startForeground(1, notification)
        mediaPlayer1.start()

        return START_STICKY
    }

    override fun onDestroy() { // Functie care se apeleaza cand se opreste serviciul
        super.onDestroy()
        mediaPlayer1.stop()
        mediaPlayer1.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() { // Functie care creaza un canal de notificari
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "CHANNEL_ID",
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java) // Se creeaza un manager de notificari
            manager.createNotificationChannel(serviceChannel) // Se creeaza un canal de notificari
        }

    }
}