package com.example.proyectofinal.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.Config.MAIL_CONTACTO
import com.example.proyectofinal.entities.Config.URL_CIUDADES
import com.example.proyectofinal.entities.Config.URL_REDDIT
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.viewmodels.ContactoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class ContactoFragment : Fragment() {

    private lateinit var btnCiudades : Button
    private lateinit var btnReddit : Button
    private lateinit var btnMail : Button
    private  val vm: ContactoViewModel by viewModels()
    private lateinit var goForm : Button

    private lateinit var consulta : TextView


    private lateinit var v : View




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_contacto, container, false)

        btnCiudades = v.findViewById(R.id.btnCiudades)
        btnReddit = v.findViewById(R.id.btnReddit)
        btnMail = v.findViewById(R.id.btnSendMail)
       // consulta = v.findViewById(R.id.consTextView)
        goForm = v.findViewById(R.id.btnForm)
        return v
    }

    override fun onStart() {
        super.onStart()

        if(ListDti.isEmpty()){

            val navBar: BottomNavigationView = activity!!.findViewById(R.id.bottom_bar)
            navBar.visibility = View.GONE

        }

        btnCiudades.setOnClickListener {

            vm.goCiudadesWeb(requireContext())
        }

        btnReddit.setOnClickListener {
            vm.goRedditWeb(requireContext())

        }

        btnMail.setOnClickListener {

            vm.sendMail(requireContext() , v)
        }

        goForm.setOnClickListener {
            val action = ContactoFragmentDirections.actionContactoFragmentToFormularioFragment()
            v.findNavController().navigate(action)
        }

       requireActivity().onBackPressedDispatcher.addCallback(this) {
            vm.dialog(requireContext() , requireActivity())
        }



    }

}