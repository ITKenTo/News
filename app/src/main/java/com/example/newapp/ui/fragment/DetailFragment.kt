package com.example.newapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newapp.R
import com.example.newapp.data.model.NewSingerton
import com.example.newapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)

        val newModel= NewSingerton.getInstance().new

        Glide.with(this).load(newModel!!.urlToImage).into(binding.img)
        binding.apply {
            tvTitle.text=newModel.title
            tvDes.text=newModel.description
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}