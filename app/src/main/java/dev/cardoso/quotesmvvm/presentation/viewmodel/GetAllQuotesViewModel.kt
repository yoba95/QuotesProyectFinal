package dev.cardoso.quotesmvvm.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.data.remote.QuoteApi
import dev.cardoso.quotesmvvm.domain.usecase.AddQuoteUseCase
import dev.cardoso.quotesmvvm.domain.usecase.GetAllQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class GetAllQuotesViewModel @Inject constructor(private val getAllQuotesUseCase: GetAllQuotesUseCase): ViewModel(){

    private val quoteResponseMutableStateFlow = MutableStateFlow(
        QuoteResponse(false, message = "",data= listOf())
    )

    val quoteResponse: StateFlow<QuoteResponse> = quoteResponseMutableStateFlow

    fun getQuotesList(token:String){
        viewModelScope.launch {
            val quoteResponse = getAllQuotesUseCase.getQuotesList(token).first()
            quoteResponse.let {
                if (quoteResponse != null){
                    quoteResponseMutableStateFlow.value=quoteResponse
                }
            }
        }
    }

}

