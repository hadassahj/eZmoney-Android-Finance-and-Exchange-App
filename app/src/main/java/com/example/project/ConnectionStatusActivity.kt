// ConnectionStatusActivity.kt
package com.example.project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.activity.ComponentActivity

class ConnectionStatusActivity : ComponentActivity() {

    private lateinit var statusTextView: TextView

    private val airplaneModeReceiver = object : BroadcastReceiver() { // BroadcastReceiver care asculta schimbarile modului avion
        override fun onReceive(context: Context?, intent: Intent?) {
            val isAirplaneModeOn = intent?.getBooleanExtra("state", false) ?: return
            updateAirplaneModeStatus(isAirplaneModeOn) // face legatura cu functia updateAirplaneModeStatus
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_status) // Face legatura cu activity_connection_status.xml

        statusTextView = findViewById(R.id.statusTextView)
        checkAirplaneModeStatus() // Verifica starea modului avion
    }

    override fun onResume() { // inregistreaza BroadcastReceiver-ul
        super.onResume()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver, filter)
    }

    override fun onPause() { // dezregistreaza BroadcastReceiver-ul
        super.onPause()
        unregisterReceiver(airplaneModeReceiver)
    }

    private fun checkAirplaneModeStatus() { // Verifica starea modului avion
        val isAirplaneModeOn = Settings.Global.getInt(
            contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0 // 0 inseamna ca modul avion este dezactivat
        ) != 0 // daca modul avion este activat, valoarea va fi diferita de 0
        updateAirplaneModeStatus(isAirplaneModeOn) // Actualizeaza starea modului avion
    }

    private fun updateAirplaneModeStatus(isAirplaneModeOn: Boolean) {
        if (isAirplaneModeOn) {
            statusTextView.text = "Airplane Mode is ON" // Daca modul avion este activat, afiseaza mesajul "Airplane Mode is ON"
        } else {
            statusTextView.text = "Airplane Mode is OFF" // Daca modul avion este dezactivat, afiseaza mesajul "Airplane Mode is OFF"
        }
    }
}