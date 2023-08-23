package com.example.newapp.ui.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newapp.R
import com.example.newapp.databinding.FragmentHomeBinding
import com.example.newapp.data.model.NewModel
import com.example.newapp.ui.adapter.NewAppAdapter
import com.example.newapp.data.viewmodel.NewSharedViewModel
import com.example.newapp.data.viewmodel.NewsViewModel
import com.example.newapp.util.Aloading
import com.example.newapp.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var list: List<NewModel>
    private lateinit var aloading: Aloading
    private lateinit var viewModel: NewsViewModel
    private val newShareViewModel: NewSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        aloading = Aloading(requireContext());
        list = ArrayList()
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
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

        viewModel.news.observe(viewLifecycleOwner) {
            list = it.articles
            val adapter = NewAppAdapter(list, requireContext(), onClick = { newModel ->
                newShareViewModel.setNewModel(newModel)
                onClick()
            })
            binding.recyNewa.adapter = adapter
            aloading.dismiss()
        }
    }

    private fun initView() {
        binding.recyNewa.layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable: Drawable? =
            ContextCompat.getDrawable(requireContext(), R.drawable.line_drable)
        drawable.let {
            if (it != null) {
                itemDecoration.setDrawable(it)
            }
        }
        binding.recyNewa.addItemDecoration(itemDecoration)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getSearch() {
        aloading.show()
        val name = binding.edSearch.text.toString()
        viewModel.getNewsSearch(name, Constant.ApiKey)
    }

    fun onClick() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_, DetailFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}