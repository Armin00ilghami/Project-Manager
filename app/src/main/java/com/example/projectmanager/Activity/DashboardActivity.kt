package com.example.projectmanager.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectmanager.Adapter.OngoingAdapter
import com.example.projectmanager.R
import com.example.projectmanager.ViewModel.MainViewModel
import com.example.projectmanager.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences( "Login User", Context.MODE_PRIVATE )

        binding.apply {
            val ongoingAdapter by lazy { OngoingAdapter(mainViewModel.loadData()) }

            viewOngoing.apply {
                adapter = ongoingAdapter
                layoutManager = GridLayoutManager(this@DashboardActivity , 2)
            }
        }

        binding.lineaProfile.setOnClickListener {

            val intent = Intent( this@DashboardActivity , ProfileActivity::class.java )
            startActivity( intent )

        }

    }
}