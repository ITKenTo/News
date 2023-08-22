package com.example.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.recyNewa.layoutManager = LinearLayoutManager(this)
//        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
//        binding.recyNewa.addItemDecoration(itemDecoration)

        supportFragmentManager.beginTransaction().add(R.id.fragment_, HomeFragment()).commit()
    }

}