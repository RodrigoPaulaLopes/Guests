package com.rodrigo.convidados.ui.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.convidados.R
import com.rodrigo.convidados.databinding.RowGuestBinding
import com.rodrigo.convidados.listners.OnGuestListner
import com.rodrigo.convidados.model.GuestModel

class GuestViewHolder(private val item: RowGuestBinding, private val listner: OnGuestListner) : ViewHolder(item.root) {


    fun bind(guest: GuestModel) {
        item.txtGuestName.text = guest.name


        item.txtGuestName.setOnClickListener {
            listner.onClick(guest.id)
        }

        item.txtGuestName.setOnLongClickListener {
            listner.onDelete(guest.id)
            true
        }
    }
}