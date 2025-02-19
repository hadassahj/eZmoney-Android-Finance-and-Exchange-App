package com.example.project

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.project.api.ExchangeRateApi
import com.example.project.api.ExchangeRateResponse
import com.example.project.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Retrofit is a type-safe HTTP client for Android and Java developed by Square.
//It simplifies the process of making network requests to RESTful web services and handling responses.
// Retrofit allows you to define your API endpoints as interfaces and automatically generates the necessary
// code to make network requests and parse responses.

class ExchangeRateActivity : ComponentActivity() {

    private lateinit var textViewRates: TextView //textView care va afisa ratele de schimb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate) // face legatura cu activity_exchange_rate.xml

        textViewRates = findViewById(R.id.textViewRates) // face legatura cu textView-ul din activity_exchange_rate.xml

        val apiKey = "1a632328c3e5190ed172813e" // cheia de acces pentru API-ul de exchange rate
        val baseCurrency = "USD"

        val api = RetrofitInstance.api
        api.getExchangeRates(apiKey, baseCurrency).enqueue(object : Callback<ExchangeRateResponse> { // face un request catre API-ul de exchange rate
            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {
                if (response.isSuccessful) { // daca request-ul a fost facut cu succes
                    val responseBody = response.body()
                    Log.d("ExchangeRateResponse", "Response: $responseBody")
                    val rates = responseBody?.conversion_rates // obtine ratele de schimb
                    val euroRate = rates?.get("EUR")    // obtine rata de schimb pentru EUR
                    val usdRate = rates?.get("USD")     // obtine rata de schimb pentru USD
                    val ronRate = rates?.get("RON")     // obtine rata de schimb pentru RON
                    val euroToRonRate = if (euroRate != null && ronRate != null) ronRate / euroRate else null // obtine rata de schimb de la EUR la RON
                    textViewRates.text = "USD-EUR: $euroRate\nUSD-USD: $usdRate\nUSD-RON: $ronRate\nEUR-RON: $euroToRonRate" // afiseaza ratele de schimb
                } else {
                    Toast.makeText(this@ExchangeRateActivity, "Failed to get rates", Toast.LENGTH_SHORT).show()
                    // daca request-ul a esuat, afiseaza o notificare de eroare
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                Toast.makeText(this@ExchangeRateActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                // daca request-ul a esuat, afiseaza o notificare de eroare
            }
        })
    }
}