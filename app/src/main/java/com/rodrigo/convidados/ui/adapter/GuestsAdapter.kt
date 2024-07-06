package com.rodrigo.convidados.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.rodrigo.convidados.databinding.RowGuestBinding
import com.rodrigo.convidados.listners.OnGuestListner
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.ui.viewHolder.GuestViewHolder

class GuestsAdapter : Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var onListner: OnGuestListner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, onListner)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuest(list: List<GuestModel>): Unit {
        guestList = list
        notifyDataSetChanged()

    }

    fun attachListner(guestListner: OnGuestListner) {
        onListner = guestListner
    }


}