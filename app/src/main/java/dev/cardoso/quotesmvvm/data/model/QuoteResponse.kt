package dev.cardoso.quotesmvvm.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class QuoteResponse (
    @SerializedName("success")
    var success:Boolean,
    @SerializedName("message")
    var message:String,
    @SerializedName("data")
    var data: List<QuoteModel>
        )
