package com.rodrigo.convidados.ui.guestForm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rodrigo.convidados.R
import com.rodrigo.convidados.databinding.ActivityGuestFormBinding
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.viewModel.guestForm.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityGuestFormBinding
    lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        binding.rbtnPresente.isChecked = true

        binding.buttonSave.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {

            val name: String = binding.edtName.text.toString()
            val presence: Boolean = binding.rbtnPresente.isChecked
            val isSaved = viewModel.save(GuestModel(0, name, presence))

            if (isSaved) Toast.makeText(this, "Saved", Toast.LENGTH_SHORT)
                .show() else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

        }
    }
}