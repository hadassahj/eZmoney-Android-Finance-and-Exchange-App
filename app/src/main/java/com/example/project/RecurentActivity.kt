package com.example.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecurentActivity : ComponentActivity() {

    private var totalValue: Double = 0.0 // va stoca valoarea totala a platilor
    private lateinit var totalValueTextView: TextView // se ia TextView-ul pentru valoarea totala
    private lateinit var recyclerView: RecyclerView // RecyclerView, mod de afisare, ideal pentru colectii de date
    private lateinit var adapter: RecurrentExpenseAdapter // un obiect care face legatura intre un RecyclerView si datele pe care acesta le afiseaza
    private val expenses = mutableListOf<RecurrentExpense>() // lista de plati recurent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recurent) // Face legatura cu activity_recurent.xml, layout-ul asociat acestei activitati

        val paymentNameEditText: EditText = findViewById(R.id.payment_name) // EditText pentru numele platii
        val paymentValueEditText: EditText = findViewById(R.id.payment_value) // EditText pentru valoarea platii
        val addPaymentButton: Button = findViewById(R.id.add_payment_button) // Butonul care adauga o plata
        totalValueTextView = findViewById(R.id.total_value) // TextView pentru valoarea totala a platilor
        recyclerView = findViewById(R.id.recycler_view) // RecyclerView pentru afisarea platilor

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecurrentExpenseAdapter(expenses) { expense -> // Adaugam un listener pentru butonul de stergere a platii
            totalValue -= expense.value // Scadem valoarea platii din valoarea totala
            totalValueTextView.text = "Total Value: $totalValue" // Actualizam TextView-ul cu valoarea totala
            saveData() // Salvam datele
        }
        recyclerView.adapter = adapter

        loadData() // Incarcam datele salvate

        addPaymentButton.setOnClickListener { // Adaugam un listener pentru butonul de adaugare a unei plati
            val paymentName = paymentNameEditText.text.toString() // Obtinem numele platii
            val paymentValue = paymentValueEditText.text.toString().toDoubleOrNull() // Obtinem valoarea platii

            if (paymentName.isNotEmpty() && paymentValue != null) {
                val expense = RecurrentExpense(paymentName, paymentValue)  // Cream un obiect RecurrentExpense
                expenses.add(expense)  // Adaugam plata in lista de plati
                adapter.notifyItemInserted(expenses.size - 1)
                totalValue += paymentValue
                totalValueTextView.text = "Total Value: $totalValue" // Actualizam TextView-ul cu valoarea totala
                paymentNameEditText.text.clear() // Stergem numele platii
                paymentValueEditText.text.clear()  // Stergem valoarea platii
                saveData() // Salvam datele
                if (totalValue > 1000) {
                    sendNotification() // Trimitem o notificare daca valoarea totala a platilor depaseste 1000
                }
            }
        }
    }

    private fun sendNotification() {
        val channelId = "expenses_channel"
        val channelName = "Expenses Notifications"
        val notificationId = 1 // Id-ul notificarii
        // Creem un obiect NotificationManager pentru a crea si gestiona notificari
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Verificam daca versiunea Android este Oreo sau mai recenta
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        // notificarea
        val notification = NotificationCompat.Builder(this, channelId) // Construim notificarea
            .setSmallIcon(R.drawable.ic_notification) // Iconita notificarii
            .setContentTitle("Expenses Alert")
            .setContentText("Total expenses have exceeded 1000!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        // in functie de versiunea Android, trimitem notificarea sau cerem permisiunea de a trimite notificari
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) { // Verificam daca versiunea Android este TIRAMISU
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                NotificationManagerCompat.from(this).notify(notificationId, notification)
            } else {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1) // Cerem permisiunea de a trimite notificari
            }
        } else {
            //la versiunile mai vechi de TIRAMISU, trimitem notificarea direct, avem acceptat de la instalare permisiunea
            NotificationManagerCompat.from(this).notify(notificationId, notification)
        }
    }

    // Functie care se apeleaza dupa ce utilizatorul a acordat sau a refuzat permisiunea de a trimite notificari
    override  fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) { // Verificam daca cererea de permisiune este cea pe care am facut-o dupa ID-ul 1
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                sendNotification() // Trimitem notificarea daca permisiunea a fost acordata
            } else {
                // Permission denied
            }
        }
    }

    // Functii pentru salvarea si incarcarea datelor din SharedPreferences

    private fun saveData() { // Salvam datele in SharedPreferences
        val sharedPreferences = getSharedPreferences("recurent_expenses", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()   // Editor pentru a modifica SharedPreferences
        val gson = Gson()  //Gson format care va fi convertit dupa in Json
        val json = gson.toJson(expenses) // Convertim lista de plati in JSON
        editor.putString("expenses_list", json)
        editor.putFloat("total_value", totalValue.toFloat()) // Convertim valoarea totala in Float
        editor.apply()
    }

    private fun loadData() { // Incarcam datele din SharedPreferences
        val sharedPreferences = getSharedPreferences("recurent_expenses", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("expenses_list", null)
        // Incarcam datele din SharedPreferences
        val type = object : TypeToken<MutableList<RecurrentExpense>>() {}.type // Tipul de date pentru lista de plati
        if (json != null) { // Verificam daca exista date salvate
            val loadedExpenses: MutableList<RecurrentExpense> = gson.fromJson(json, type)
            expenses.addAll(loadedExpenses)// Incarcam platile in lista de plati
            adapter.notifyDataSetChanged()
        }
        totalValue = sharedPreferences.getFloat("total_value", 0.0f).toDouble() // Incarcam valoarea totala
        totalValueTextView.text = "Total Value: $totalValue" // Actualizam TextView-ul cu valoarea totala
    }
}