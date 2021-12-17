package dev.cardoso.quotesmvvm.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityAddQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityDeleteQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.AddQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.DeleteQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DeleteQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val deleteQuoteViewModel: DeleteQuoteViewModel by viewModels()

    private var token=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@DeleteQuoteActivity)
        setContentView(binding.root)
        getToken()
        binding.btDelete.setOnClickListener {
            val id = binding.etIdDelete.text.toString().toInt()
            val quote = ""
            val author = ""
            val quoteModel = QuoteModel(id=id, quote=quote, author=author)
            lifecycleScope.launch(Dispatchers.IO){
                Log.e("TOKEN", token)
                deleteQuoteViewModel.deleteQuote(quoteModel,"Bearer $token")
            }
            clear()
        }
        binding.imageButtonRegresar.setOnClickListener {
            regresarView()
        }
        deleteQuoteViewModel.quoteResponse.let{ }
        observer()
    }

    private fun getToken(){
        lifecycleScope.launch(Dispatchers.IO){
            userPreferencesRepository.token.collect{ token = it}
        }
    }
    private fun clear(){
        with(binding){
            etIdDelete.setText("")
            etIdDelete.requestFocus()
        }
    }

    private fun observer(){
        lifecycleScope.launch(Dispatchers.IO){
            deleteQuoteViewModel.quoteResponse.collect{
                binding.tvMessageDelete.text= it.message

            }
        }
    }
    fun regresarView(){
        finish()
    }

}