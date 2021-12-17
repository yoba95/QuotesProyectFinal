package dev.cardoso.quotesmvvm.domain.usecase

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.google.gson.Gson

import com.google.gson.JsonElement

import com.google.gson.JsonParseException

import com.google.gson.JsonDeserializationContext

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.Retrofit
import java.lang.reflect.Type


class GetAllQuotesUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {


    suspend fun getQuotesList(token: String): Flow<QuoteResponse?> =
        quoteRepository.getQuotesList(token)




}