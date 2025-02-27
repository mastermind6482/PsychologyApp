package com.example.psychologyapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.psychologyapp.data.Meditation
import com.example.psychologyapp.databinding.ItemMeditationBinding

class MeditationAdapter(
    private val meditations: List<Meditation>,
    private val onClick: (Meditation) -> Unit
) : RecyclerView.Adapter<MeditationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMeditationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meditations[position])
    }

    override fun getItemCount(): Int = meditations.size

    inner class ViewHolder(private val binding: ItemMeditationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meditation: Meditation) {
            binding.tvTitle.text = meditation.title
            binding.root.setOnClickListener { onClick(meditation) }
        }
    }
}