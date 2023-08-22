package com.example.newapp.ui.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.R
import com.example.newapp.databinding.FragmentHomeBinding
import com.example.newapp.data.model.NewModel
import com.example.newapp.data.model.NewSingerton
import com.example.newapp.ui.adapter.NewAppAdapter
import com.example.newapp.data.network.Iclick
import com.example.newapp.data.network.NewApi
import com.example.newapp.data.network.RetrofitHelper
import com.example.newapp.data.repository.NewsRepository
import com.example.newapp.data.viewmodel.NewsModelFactory
import com.example.newapp.data.viewmodel.NewsViewModel
import com.example.newapp.util.Aloading
import com.example.newapp.util.Constant

class HomeFragment : Fragment() , Iclick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var api: NewApi
    private lateinit var list: List<NewModel>
    private lateinit var aloading: Aloading
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        aloading = Aloading(requireContext());
        list = ArrayList()
        api = RetrofitHelper.getIntance().create(NewApi::class.java)
        val newsRepository = NewsRepository(api)
        viewModel = ViewModelProvider(this,NewsModelFactory(newsRepository))[NewsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        binding.edSearch.setOnClickListener {
            if (binding.edSearch.text.toString().isNotEmpty()) {
                getSearch()
            } else {
                Toast.makeText(requireContext(), "Nhập dữ liệu để tìm kiếm", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.news.observe(viewLifecycleOwner){
            list=it.articles
            val adapter = NewAppAdapter(list, this@HomeFragment, requireContext())
            binding.recyNewa.adapter = adapter
            adapter.notifyDataSetChanged()
            aloading.dismiss()
        }


    }

    private fun initView(){
        binding.recyNewa.layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.line_drable) // Thay R.drawable.divider bằng drawable bạn đã tạo
        itemDecoration.setDrawable(drawable!!)
        binding.recyNewa.addItemDecoration(itemDecoration)
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun getSearch() {
        aloading.show()
        val name = binding.edSearch.text.toString()
        viewModel.getNewsSearch(name,Constant.ApiKey)
    }

    override fun onClick(pos: Int) {
        val newModel: NewModel = list[pos]
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_, DetailFragment())
        transaction.addToBackStack(null)
        transaction.commit()
        NewSingerton.getInstance().new = newModel
    }


}