package com.example.crop2cash.network

import com.example.crop2cash.data.model.ExhibitClass
import com.example.crop2cash.data.model.ExhibitClassItem
import retrofit2.Response
import retrofit2.http.GET

interface CropCashApi {

    @GET("Reyst/exhibit_db/list")
    suspend fun getList(): Response <ExhibitClass>
}