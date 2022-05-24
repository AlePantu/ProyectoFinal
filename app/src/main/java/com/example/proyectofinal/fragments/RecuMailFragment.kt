package com.example.proyectofinal.fragments

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.proyectofinal.R
import com.example.proyectofinal.viewmodels.RecuMailViewModel
import com.google.firebase.auth.FirebaseAuth


class RecuMailFragment : Fragment() {

    private lateinit var v : View

    private  val vm: RecuMailViewModel by viewModels()

    private lateinit var mail : TextView
    private lateinit var btnEnviar : Button
    private lateinit var btnVolver : Button
    private lateinit var emailRecu : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_recu_mail, container, false)

        mail = v.findViewById(R.id.recuMailTextView)
        btnEnviar = v.findViewById(R.id.btnEnviar)
        btnVolver = v.findViewById(R.id.btnBack)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        btnEnviar.setOnClickListener {

            emailRecu = mail.text.toString()

            if(!emailRecu.isEmpty()){
                vm.resetPassword(emailRecu , v)

            } else {
                Toast.makeText(context , "Debe ingresar el mail" , Toast.LENGTH_SHORT).show()
            }

        }

        btnVolver.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}