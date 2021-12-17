package dev.cardoso.quotesmvvm.presentation.view



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityGetAllQuotesBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.GetAllQuotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class GetAllQuotesActivity()  : AppCompatActivity() {

    private lateinit var binding: ActivityGetAllQuotesBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val getAllViewModel: GetAllQuotesViewModel by viewModels()
    private var token=""

    private lateinit var listQuote : List<QuoteModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetAllQuotesBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@GetAllQuotesActivity)
        setContentView(binding.root)
        getAllViewModel.quoteResponse.let{ }
        observer()
       Log.d("data", observer().toString())

        binding.imageButtonRegresar.setOnClickListener {
            regresarView()
        }

    }

private fun observer(){
    lifecycleScope.launch(Dispatchers.IO){
        getAllViewModel.quoteResponse.collect{
            getAllViewModel.getQuotesList(token)
            binding.tvMessage.text= it.data.toString()
    }
}
}
    fun regresarView(){
        finish()
    }

}
