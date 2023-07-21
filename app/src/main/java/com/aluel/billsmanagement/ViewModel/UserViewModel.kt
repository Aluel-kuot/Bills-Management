package com.aluel.billsmanagement.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aluel.billsmanagement.Model.RegisterRequest
import com.aluel.billsmanagement.Model.RegisterResponse
import com.aluel.billsmanagement.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel
    (){
        val userRepo=UserRepository()
    val regLiveData=MutableLiveData<RegisterResponse>()
    var errorLiveData=MutableLiveData <String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            var response=userRepo.registerUser(registerRequest)
            if(response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}