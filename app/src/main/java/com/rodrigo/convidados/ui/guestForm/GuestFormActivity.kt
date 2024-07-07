package com.rodrigo.convidados.ui.guestForm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rodrigo.convidados.R
import com.rodrigo.convidados.databinding.ActivityGuestFormBinding
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.viewModel.guestForm.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityGuestFormBinding
    lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        binding.rbtnPresente.isChecked = true

        binding.buttonSave.setOnClickListener(this)


        loadParams()
        observer()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {

            val name: String = binding.edtName.text.toString()
            val presence: Boolean = binding.rbtnPresente.isChecked

            viewModel.save(GuestModel(guestId, name, presence))

        }
    }


    private fun observer() {
        viewModel.guest.observe(this, {
            binding.edtName.setText(it.name)
            if (it.presence) {
                binding.rbtnPresente.isChecked = true
            } else {
                binding.rbtnAusente.isChecked = true
            }
        })

        viewModel.isSaved.observe(this, {
            if (it != "Falha") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }else {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun loadParams() {
        val bundle = intent.extras

        if (bundle != null) {
            val id = bundle.getInt("guestId")
            guestId = id
            viewModel.get(id)
            binding.buttonSave.text = "Update"
        }
    }
}