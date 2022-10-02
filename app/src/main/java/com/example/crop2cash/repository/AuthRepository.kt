package com.example.crop2cash.repository

import com.example.crop2cash.data.model.ExhibitClass
import com.example.crop2cash.data.model.ExhibitClassItem
import com.example.crop2cash.network.CropCashApi
import com.example.crop2cash.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: CropCashApi) {
    suspend fun getImages(): Response<ExhibitClass> {
        return api.getList()
    }
}