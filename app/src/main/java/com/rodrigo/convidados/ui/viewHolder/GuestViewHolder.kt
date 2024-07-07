package com.rodrigo.convidados.ui.viewHolder

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodrigo.convidados.R
import com.rodrigo.convidados.databinding.RowGuestBinding
import com.rodrigo.convidados.listners.OnGuestListner
import com.rodrigo.convidados.model.GuestModel

class GuestViewHolder(private val item: RowGuestBinding, private val listner: OnGuestListner) :
    ViewHolder(item.root) {


    fun bind(guest: GuestModel) {
        item.txtGuestName.text = guest.name


        item.txtGuestName.setOnClickListener {
            listner.onClick(guest.id)
        }

        item.txtGuestName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado").setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which -> listner.onDelete(guest.id)}
                .setNegativeButton("Não") { dialog, which -> null}
                .create()
                .show()

            true
        }
    }
}