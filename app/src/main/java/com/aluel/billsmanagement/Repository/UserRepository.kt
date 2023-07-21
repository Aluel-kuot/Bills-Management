package com.aluel.billsmanagement.Repository

import com.aluel.billsmanagement.API.ApiClient
import com.aluel.billsmanagement.API.ApiInterface
import com.aluel.billsmanagement.Model.RegisterRequest
import com.aluel.billsmanagement.Model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient =ApiClient.buildClient(ApiInterface::class.java)

    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
}



