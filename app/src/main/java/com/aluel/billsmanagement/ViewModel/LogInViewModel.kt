package com.aluel.billsmanagement.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aluel.billsmanagement.Model.LoginRequest
import com.aluel.billsmanagement.Model.LogInResponse
import com.aluel.billsmanagement.Repository.LogInRepo
import kotlinx.coroutines.launch

class LogInViewModel:ViewModel() {
    val logInRepo = LogInRepo()
    val logLiveData = MutableLiveData<LogInResponse>()
    var errorsLiveData = MutableLiveData<String>()

    fun logInUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = logInRepo.loginUser(loginRequest)
            if (response.isSuccessful) {
                logLiveData.postValue(response.body())
            } else {
                errorsLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}
