package com.example.psychologyapp.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psychologyapp.data.Meditation
import com.example.psychologyapp.databinding.FragmentMeditationBinding
import com.example.psychologyapp.viewmodel.MeditationViewModel

class MeditationFragment : Fragment() {
    private var _binding: FragmentMeditationBinding? = null
    private val binding get() = _binding!!
    private var timer: CountDownTimer? = null
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
        binding.btnStart.setOnClickListener {
            viewModel.meditations.value?.firstOrNull()?.let { startTimer(it.duration) }
        }
        binding.btnStop.setOnClickListener {
            timer?.cancel()
            binding.tvTimer.text = "00:00"
        }
    }

    private fun setupRecyclerView() {
        binding.rvMeditations.layoutManager = LinearLayoutManager(context)
        viewModel.meditations.observe(viewLifecycleOwner) { meditations ->
            binding.rvMeditations.adapter = MeditationAdapter(meditations) { meditation ->
                startTimer(meditation.duration)
            }
        }
    }

    private fun startTimer(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.tvTimer.text = "Готово!"
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}