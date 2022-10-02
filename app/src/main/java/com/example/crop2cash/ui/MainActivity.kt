package com.example.crop2cash.ui

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.crop2cash.R
import com.example.crop2cash.databinding.ActivityMainBinding
import com.example.crop2cash.utils.ConnectivityLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var connectivityLiveData: ConnectivityLiveData

    //    private lateinit var image: Image
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(this)
        setTheme(R.style.Theme_Crop2Cash)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectivityLiveData.observe(this, { networkState ->
            if (networkState == true) {
                binding.networkError.visibility = View.GONE
            } else binding.networkError.visibility = View.VISIBLE
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> Toast.makeText(this, "You click on favorite", Toast.LENGTH_SHORT).show()
            R.id.favorite -> Toast.makeText(this, "You click on favorite", Toast.LENGTH_SHORT)
                .show()
            R.id.more -> Toast.makeText(this, "You click on favorite", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}