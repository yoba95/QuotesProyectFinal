package dev.cardoso.quotesmvvm.data.remote

import dev.cardoso.quotesmvvm.core.API_PATH
import dev.cardoso.quotesmvvm.core.PATH_QUOTE
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.DELETE
import okhttp3.ResponseBody
import retrofit2.Call


interface QuoteApi {
    @GET("$API_PATH")
    suspend fun getQuotes(): Response<List<QuoteModel>>
    suspend fun getQuote(quoteId: Int): Any

    @Headers("Context-Type: application/json; charset=utf-8", "Accept: */*; charset=utf-8")
    @PUT("$PATH_QUOTE/{id}")
    suspend fun editQuote(@Header("Authorization") token:String,
    @Path("id") id:Int,
    @Body quoteModel: QuoteModel): Response<QuoteResponse>

    @Headers("Context-Type: application/json; charset=utf-8", "Accept: */*; charset=utf-8")
    @POST("$PATH_QUOTE")
    suspend fun addQuote(@Header("Authorization") token: String,
                         @Body quoteModel: QuoteModel): Response<QuoteResponse>

    @Headers("Context-Type: application/json; charset=utf-8", "Accept: */*; charset=utf-8")
    @DELETE("$PATH_QUOTE/{id}")
    suspend fun deleteQuote(@Header("Authorization") token: String,
    @Path("id") id: Int): Response<QuoteResponse>

    @Headers("Context-Type: application/json; charset=utf-8", "Accept: */*; charset=utf-8")
    @GET("$PATH_QUOTE")
    suspend fun getQuotesList(@Header("Authorization") token: String): Response<QuoteResponse>
//




}