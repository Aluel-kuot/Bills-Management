package com.aluel.billsmanagement.Repository

import com.aluel.billsmanagement.API.ApiClient
import com.aluel.billsmanagement.API.ApiInterface
import com.aluel.billsmanagement.Model.LogInResponse
import com.aluel.billsmanagement.Model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LogInRepo {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest): Response<LogInResponse> {
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }
}

