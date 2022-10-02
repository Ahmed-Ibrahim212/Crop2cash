package com.example.crop2cash.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crop2cash.data.model.ExhibitClass
import com.example.crop2cash.repository.AuthRepository
import com.example.crop2cash.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CropCashViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private var _userResponse = MutableLiveData<Resource<ExhibitClass>>()
    val userResponse: MutableLiveData<Resource<ExhibitClass>>get() = _userResponse

    fun fetchCar(){
        _userResponse.postValue(Resource.Loading(null, "Loading..."))
        viewModelScope.launch(Dispatchers.IO) {
            val  result = authRepository.getImages()
            Log.d("apidata", result.body().toString())
            _userResponse.postValue(handleProductData(result))
        }
    }
    private fun handleProductData(userData: Response<ExhibitClass>): Resource<ExhibitClass> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }
}

