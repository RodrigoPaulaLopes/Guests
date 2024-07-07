package com.rodrigo.convidados.ui.present

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodrigo.convidados.databinding.FragmentPresetBinding
import com.rodrigo.convidados.listners.OnGuestListner
import com.rodrigo.convidados.ui.adapter.GuestsAdapter
import com.rodrigo.convidados.ui.guestForm.GuestFormActivity
import com.rodrigo.convidados.viewModel.guest.GuestsViewModel


class PresetFragment : Fragment() {

    private var _binding: FragmentPresetBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    lateinit var viewModel: GuestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresetBinding.inflate(inflater, container, false)
        //layout
        binding.reclyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adpter
        binding.reclyclerAllGuests.adapter = adapter

        var listner = object : OnGuestListner {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt("guestId", id)
                startActivity(Intent(context, GuestFormActivity::class.java).putExtras(bundle))

            }

            override fun onDelete(id: Int) {

                viewModel.delete(id)
                viewModel.getPresents()
            }

        }

        adapter.attachListner(listner)
        viewModel.getPresents()
        observer()
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.getPresents()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuest(it)
        }
    }
}