package com.example.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.ui.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.fragment_, HomeFragment()).commit()
    }

}