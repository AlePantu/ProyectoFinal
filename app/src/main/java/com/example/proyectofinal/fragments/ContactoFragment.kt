package com.example.proyectofinal.fragments

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.viewmodels.ContactoViewModel
import com.google.firebase.auth.FirebaseAuth


class ContactoFragment : Fragment() {

    private lateinit var btnCiudades : Button
    private lateinit var btnReddit : Button
    private lateinit var btnMail : Button
    private  val vm: ContactoViewModel by viewModels()
    private lateinit var goForm : Button

    private lateinit var consulta : TextView


    private lateinit var v : View

    private val url_ciudades = "https://ciudadesdelfuturo.org.ar/"
    private val url_reddti = "https://www.reddti-ar.com.ar/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_contacto, container, false)

        btnCiudades = v.findViewById(R.id.btnCiudades)
        btnReddit = v.findViewById(R.id.btnReddit)
        btnMail = v.findViewById(R.id.btnSendMail)
        consulta = v.findViewById(R.id.consTextView)
        goForm = v.findViewById(R.id.btnForm)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnCiudades.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url_ciudades)
            startActivity(i)

        }

        btnReddit.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url_reddti)
            startActivity(i)

        }

        btnMail.setOnClickListener {

            val mailto = "mailto:alepantuliano@hotmail.com"

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT ,"Consulta")
            emailIntent.putExtra(Intent.EXTRA_TEXT ,consulta.text.toString())

            startActivity(emailIntent)

        }

        goForm.setOnClickListener {
            val action = ContactoFragmentDirections.actionContactoFragmentToFormularioFragment()
            v.findNavController().navigate(action)

        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            AlertDialog.Builder(requireContext())
                .setMessage("Cerrar Aplicacion?")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { dialog, whichButton ->
                    FirebaseAuth.getInstance().signOut()
                    vm.cleanLogUser()
                    activity?.finish()
                }
                .setNegativeButton("Cancelar") { dialog, whichButton ->

                }
                .show()
        }

    }

}