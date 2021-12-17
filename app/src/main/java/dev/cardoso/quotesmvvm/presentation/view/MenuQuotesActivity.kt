package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.preferencesDataStore
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.databinding.ActivityMenuQuotesBinding

class MenuQuotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuQuotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        print(this.applicationContext)
        super.onCreate(savedInstanceState)
        binding= ActivityMenuQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonAdd.setOnClickListener{
            runViewAdd()
        }
        binding.imageButton2Delete.setOnClickListener {
            runViewADelete()
        }
        binding.imageButton3Update.setOnClickListener {
            runViewUpdate()
        }
        binding.imageButton4List.setOnClickListener {
            runViewList()
        }
    }

    fun runViewAdd(){
        val intent = Intent(this, AddQuoteActivity::class.java)
        startActivity(intent)
    }
    fun runViewADelete(){
        val intent = Intent(this, DeleteQuoteActivity::class.java)
        startActivity(intent)
    }
    fun runViewUpdate(){
        val intent = Intent(this, EditQuoteActivity::class.java)
        startActivity(intent)
    }
    fun runViewList(){
        val intent = Intent(this, GetAllQuotesActivity::class.java)
        startActivity(intent)
    }
    fun cerrarsesion(){
      //  val intent = Intent(this, LoginActivity::class.java)
    }



}