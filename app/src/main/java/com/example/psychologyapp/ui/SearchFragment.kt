package com.example.psychologyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologyapp.databinding.FragmentSearchBinding
import com.example.psychologyapp.viewmodel.SearchViewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: MeditationAdapter // Используем MeditationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            viewModel.searchMeditations(query)
        }
        viewModel.meditations.observe(viewLifecycleOwner) { meditations ->
            adapter = MeditationAdapter(meditations) { /* Обработка клика, если нужно */ }
            binding.rvResults.adapter = adapter
        }
    }

    private fun setupRecyclerView() {
        binding.rvResults.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}