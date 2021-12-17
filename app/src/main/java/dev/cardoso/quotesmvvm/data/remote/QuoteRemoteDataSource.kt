package dev.cardoso.quotesmvvm.data.remote

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import kotlinx.coroutines.flow.Flow

interface QuoteRemoteDataSource {
    suspend  fun getQuotes(): Flow<List<QuoteModel>?>
    //suspend  fun getQuotesAll(): Flow<List<QuoteResponse>?>
    suspend fun editQuote(quoteModel: QuoteModel, token:String):Flow<QuoteResponse?>
    suspend fun addQuote(quoteModel: QuoteModel, token:String):Flow<QuoteResponse?>
    suspend fun deleteQuote(quoteModel: QuoteModel, token: String):Flow<QuoteResponse?>
    suspend fun getQuotesList(token: String): Flow<QuoteResponse?>


}