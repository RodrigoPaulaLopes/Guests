package com.rodrigo.convidados.ui.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.rodrigo.convidados.databinding.FragmentAllGuestsBinding
import com.rodrigo.convidados.listners.OnGuestListner
import com.rodrigo.convidados.ui.adapter.GuestsAdapter
import com.rodrigo.convidados.viewModel.guest.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)


        //layout
        binding.reclyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adpter
        binding.reclyclerAllGuests.adapter = adapter

        var listner = object  : OnGuestListner {
            override fun onClick(id: Int) {
                Toast.makeText(context, "Clicked ${id}", Toast.LENGTH_LONG).show()
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.attachListner(listner)

        viewModel.getAll()
        observer()



        return binding.root
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