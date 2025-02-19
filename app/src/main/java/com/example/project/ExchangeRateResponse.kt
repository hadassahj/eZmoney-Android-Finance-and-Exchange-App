package com.example.project.api

data class ExchangeRateResponse( //clasa care contine raspunsul primit de la API-ul de exchange rate
    val result: String,
    val base_code: String,
    val conversion_rates: Map<String, Double>
)