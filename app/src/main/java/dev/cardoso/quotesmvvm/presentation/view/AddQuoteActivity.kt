package dev.cardoso.quotesmvvm.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.data.model.LoginRequest
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityAddQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityLoginBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.AddQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.EditQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val addQuoteViewModel: AddQuoteViewModel by viewModels()

    private var token=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@AddQuoteActivity)
        setContentView(binding.root)
        getToken()
        binding.btAgregar.setOnClickListener {
            val id = binding.etIdAdd.text.toString().toInt()
            val quote = binding.etQuoteAdd.text.toString()
            val author = binding.etAuthorAdd.text.toString()
            val quoteModel = QuoteModel(id=id, quote=quote, author=author)

            lifecycleScope.launch(Dispatchers.IO){
              //  Log.e("TOKEN", token)
                addQuoteViewModel.addQuote(quoteModel,"Bearer $token")
            }
            clear()
        }
        binding.imageButtonRegresar.setOnClickListener {
            regresarView()
        }
        addQuoteViewModel.quoteResponse.let{ }
        observer()
    }

    private fun getToken(){
        lifecycleScope.launch(Dispatchers.IO){
            userPreferencesRepository.token.collect{ token = it}
        }
    }

    private fun observer(){
        lifecycleScope.launch(Dispatchers.IO){
            addQuoteViewModel.quoteResponse.collect{
            binding.tvMessageAdd.text= it.message

            }
        }
    }
    private fun clear(){
        with(binding){
            etIdAdd.setText("")
            etQuoteAdd.setText("")
            etAuthorAdd.setText("")
            etIdAdd.requestFocus()
        }
    }
    fun regresarView(){
        finish()
    }

    /*
private fun observer(){
    lifecycleScope.launch(Dispatchers.IO){
        addQuoteViewModel.quoteResponse.collect{
            if(it.success){
                Toast.makeText(baseContext, "Nota guardad corrctamente", Toast.LENGTH_LONG).show()
            }else{
                if (it.message!=""){
                    Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}*/

/*
    private lateinit var binding: ActivityAddQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val addQuoteViewModel: AddQuoteViewModel by viewModels()

    private var token=""
    override fun onCreate(savedInstanceState: Bundle?){
        print(this.applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@AddQuoteActivity)
        setContentView(binding.root)
        getToken()
        binding.btAgregar.setOnClickListener {
            val id = binding.etIdAdd.text.toString().toInt()
            val quote = binding.etQuoteAdd.text.toString()
            val author = binding.etAuthorAdd.text.toString()
            val quoteModel = QuoteModel(id=id, quote=quote, author=author)
            lifecycleScope.launch(Dispatchers.IO){
                Log.e("TOKEN", token)
                addQuoteViewModel.addQuote(quoteModel, "Bearer $token")
            }
        }
        addQuoteViewModel.quoteResponse.let{ }
        observer()

    }


    private fun getToken(){
        lifecycleScope.launch(Dispatchers.IO){
            userPreferencesRepository.token.collect{ token = it}
        }
    }

    private fun observer(){
        lifecycleScope.launch(Dispatchers.IO){
            addQuoteViewModel.quoteResponse.collect{
                binding.tvMessageAdd.text= it.message
            }
        }
    }

 */

}







