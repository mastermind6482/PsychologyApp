package com.example.psychologyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologyapp.databinding.FragmentMeditationBinding
import com.example.psychologyapp.viewmodel.MeditationViewModel

class MeditationFragment : Fragment() {
    private var _binding: FragmentMeditationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MeditationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.btnStart.visibility = View.GONE // Убираем кнопки из списка
        binding.btnStop.visibility = View.GONE
        binding.tvTimer.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.rvMeditations.layoutManager = LinearLayoutManager(context)
        viewModel.meditations.observe(viewLifecycleOwner) { meditations ->
            binding.rvMeditations.adapter = MeditationAdapter(meditations) { meditation ->
                val action = MeditationFragmentDirections.actionMeditationToDetail(
                    title = meditation.title,
                    duration = meditation.duration,
                    instruction = meditation.instruction,
                    imageUrl = meditation.imageUrl
                )
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}