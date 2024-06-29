package com.rodrigo.convidados.ui.present

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rodrigo.convidados.databinding.FragmentPresetBinding
import com.rodrigo.convidados.viewModel.present.PresetViewModel

class PresetFragment : Fragment() {

    private var _binding: FragmentPresetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(PresetViewModel::class.java)

        _binding = FragmentPresetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPreset
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}