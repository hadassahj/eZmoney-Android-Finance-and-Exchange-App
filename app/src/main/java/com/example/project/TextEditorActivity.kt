package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class TextEditorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_editor)

        val editText = findViewById<EditText>(R.id.editText)
        val buttonShare = findViewById<Button>(R.id.buttonShare) // buton pentru share

        buttonShare.setOnClickListener { // la apasarea butonului se apeleaza functia shareText
            val text = editText.text.toString() // se preia textul introdus
            if (text.isNotEmpty()) {
                shareText(text)
            }
        }
    }

    private fun shareText(text: String) { // functie care primeste un text si il trimite catre alte aplicatii
        val intent = Intent().apply {
            action = Intent.ACTION_SEND // actiunea de share
            putExtra(Intent.EXTRA_TEXT, text) // ataseaza textul care va fi trimis
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, "Share via")) // se deschide o lista cu aplicatiile care pot primi textul
    }
}