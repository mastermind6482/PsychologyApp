package com.example.psychologyapp.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.psychologyapp.R
import com.example.psychologyapp.databinding.FragmentMeditationDetailBinding

class MeditationDetailFragment : Fragment() {
    private var _binding: FragmentMeditationDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MeditationDetailFragmentArgs by navArgs()
    private var mediaPlayer: MediaPlayer? = null
    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var remainingTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeditationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupMediaPlayer()
        setupButtons()
    }

    private fun setupUI() {
        binding.tvInstruction.text = args.instruction ?: "Нет инструкции"
        remainingTime = args.duration
        updateTimerText(remainingTime)
        binding.pbTimer.max = (args.duration / 1000).toInt()
        binding.pbTimer.progress = (remainingTime / 1000).toInt()
    }

    private fun setupMediaPlayer() {
        mediaPlayer = MediaPlayer.create(context, R.raw.relax_music)
        mediaPlayer?.isLooping = true
    }

    private fun setupButtons() {
        binding.btnStartPause.setOnClickListener {
            if (isRunning) {
                pauseMeditation()
            } else {
                startMeditation()
            }
        }
        binding.btnStop.setOnClickListener {
            stopMeditation()
        }
    }

    private fun startMeditation() {
        if (!isRunning) {
            isRunning = true
            binding.btnStartPause.text = "Пауза"
            mediaPlayer?.start()

            timer = object : CountDownTimer(remainingTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingTime = millisUntilFinished
                    val progress = (millisUntilFinished / 1000).toInt()
                    binding.pbTimer.progress = progress
                    updateTimerText(millisUntilFinished)
                }

                override fun onFinish() {
                    stopMeditation()
                }
            }.start()
        }
    }

    private fun pauseMeditation() {
        isRunning = false
        binding.btnStartPause.text = "Старт"
        mediaPlayer?.pause()
        timer?.cancel()
    }

    private fun stopMeditation() {
        isRunning = false
        binding.btnStartPause.text = "Старт"
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
        timer?.cancel()
        remainingTime = args.duration
        binding.pbTimer.progress = (remainingTime / 1000).toInt()
        updateTimerText(remainingTime)
    }

    private fun updateTimerText(millis: Long) {
        val minutes = millis / 1000 / 60
        val seconds = (millis / 1000) % 60
        binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
        timer?.cancel()
        _binding = null
    }
}