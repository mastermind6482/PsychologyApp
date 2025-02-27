package com.example.psychologyapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.psychologyapp.databinding.FragmentPsychologyDetailBinding
import com.example.psychologyapp.viewmodel.PsychologyViewModel

class PsychologyDetailFragment : Fragment() {
    private var _binding: FragmentPsychologyDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PsychologyViewModel by viewModels()
    private val args: PsychologyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPsychologyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvArticleTitle.text = args.title
        binding.tvArticleContent.text = args.content
        binding.etNotes.setText(viewModel.notes.value)
        binding.etNotes.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateNotes(s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}