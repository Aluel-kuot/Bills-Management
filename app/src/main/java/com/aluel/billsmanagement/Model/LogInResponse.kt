package com.aluel.billsmanagement.Model

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    var message:String,
    @SerializedName ("user_id")var userId:User,
    @SerializedName("access_token")var accessToken:String

)