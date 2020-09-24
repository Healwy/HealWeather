package com.xuniyishifanchen.healweather.logic.network

import com.xuniyishifanchen.healweather.HealWeatherApplication
import com.xuniyishifanchen.healweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${HealWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}