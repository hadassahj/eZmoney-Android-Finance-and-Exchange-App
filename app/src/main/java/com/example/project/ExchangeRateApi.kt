package com.example.project.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi { //interfata care contine metoda de a face un request catre API-ul de exchange
    @GET("v6/{apiKey}/latest/{base}") // face un request catre API-ul de exchange rate cu datele noastre
    fun getExchangeRates( //face un request catre API-ul de exchange rate
        @Path("apiKey") apiKey: String, //cheia de acces pentru API-ul de exchange rate
        @Path("base") base: String //valuta de baza
    ): Call<ExchangeRateResponse> //returneaza un obiect de tip ExchangeRateResponse
}