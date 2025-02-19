package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Facecm legatura cu activity_main.xml, layout-ul asociat acestei activitati

        // Butoanele de mai jos sunt butoane care deschid activitatile aplicatiei

        val recurentButton: Button = findViewById(R.id.recurent_button) // Butonul initializeaza un intent care deschide RecurentActivity
        recurentButton.setOnClickListener {
            val intent = Intent(this, RecurentActivity::class.java)
            startActivity(intent)
        }

        val buttonOpenEditor = findViewById<Button>(R.id.buttonOpenEditor)  // Butonul initializeaza un intent care deschide TextEditorActivity
        buttonOpenEditor.setOnClickListener {
            val intent = Intent(this, TextEditorActivity::class.java)
            startActivity(intent)
        }

        val exchangeButton = findViewById<Button>(R.id.exchange_button) // Butonul initializeaza un intent care deschide ExchangeRateActivity
        exchangeButton.setOnClickListener {
            val intent = Intent(this, ExchangeRateActivity::class.java)
            startActivity(intent)
        }

        val connectionStatusButton = findViewById<Button>(R.id.connection_status_button) // Butonul initializeaza un intent care deschide ConnectionStatusActivity`
        connectionStatusButton.setOnClickListener {
            val intent = Intent(this, ConnectionStatusActivity::class.java)
            startActivity(intent)
        }

        val infoButton = findViewById<Button>(R.id.info_button) // Butonul initializeaza un intent care deschide InfoActivity
        infoButton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

    }

}

