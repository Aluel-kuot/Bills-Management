package com.aluel.billsmanagement.Model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var firstName:String,
    @SerializedName("second_name") var secondName:String,
    @SerializedName("email")  var email:String,
    var phoneNumber:String,
    var password:String,

    )

