package com.aluel.billsmanagement.API

import com.aluel.billsmanagement.Model.LogInResponse
import com.aluel.billsmanagement.Model.LoginRequest
import com.aluel.billsmanagement.Model.RegisterRequest
import com.aluel.billsmanagement.Model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
   suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

   @POST("/users/login")
   suspend fun loginUser(@Body LoginRequest:LoginRequest ):Response<LogInResponse>

}