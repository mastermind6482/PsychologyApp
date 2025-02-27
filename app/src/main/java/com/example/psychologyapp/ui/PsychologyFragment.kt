package com.example.psychologyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologyapp.databinding.FragmentPsychologyBinding
import com.example.psychologyapp.viewmodel.PsychologyViewModel

class PsychologyFragment : Fragment() {
    private var _binding: FragmentPsychologyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PsychologyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPsychologyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvArticles.layoutManager = LinearLayoutManager(context)
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            binding.rvArticles.adapter = PsychologyAdapter(articles) { article ->
                val action = PsychologyFragmentDirections.actionPsychologyToDetail(article.title, article.content)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}