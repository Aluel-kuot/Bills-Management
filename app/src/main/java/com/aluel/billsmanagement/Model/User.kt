package com.aluel.billsmanagement.Model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name") var firstName:String,
    @SerializedName("second_name") var secondName:String,
    var email:String,
    @SerializedName("phone_number")  var phoneNumber:String,
    @SerializedName("user_id") var userId:String,



    )
