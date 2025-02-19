package com.example.project.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit este o librarie care se foloseste pentru a face requesturi catre un api
//aceasta clasa se foloseste pentru a crea un obiect retrofit
//care se foloseste pentru a face requesturi catre api ul de la care se iau datele


object RetrofitInstance {
    private const val BASE_URL = "https://v6.exchangerate-api.com/" // url care este folosit pentru api ul de la care se iau datele

    // Gson este o librarie facuta de Google care se foloseste pentru a converti obiecte Java/Kotlin in json si invers
    private val gson = GsonBuilder() // se foloseste pentru a converti datele primite de la api in obiecte si apoi in json
        .setLenient() // va fi mai permisiv in ceea ce priveste datele care sunt convertite pentru json
        .create() // se creeaza un obiect gson

    private val retrofit by lazy { // un obiect retrofit care se foloseste pentru a face requesturi catre api
        Retrofit.Builder()  //maniera lazy este folosita pentru a crea obiectul retrofit cand e prima data folosit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: ExchangeRateApi by lazy {  // va fi initializat doar cand e prima data folosit
        retrofit.create(ExchangeRateApi::class.java) // se foloseste pentru a implementa interfata ExchangeRateApi
    }
}