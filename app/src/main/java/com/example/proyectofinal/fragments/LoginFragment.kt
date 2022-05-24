package com.example.proyectofinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.Favoritos

import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.LoginViewModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    private val vm: LoginViewModel by viewModels()

    private lateinit var email : TextView
    private lateinit var pass : TextView
    private lateinit var btnLog : Button
    private lateinit var btnReg : Button
    private lateinit var btnRecu : Button
    private lateinit var mail : String

    private lateinit var v : View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         v = inflater.inflate(R.layout.fragment_login, container, false)

        email = v.findViewById(R.id.emailText)
        pass = v.findViewById(R.id.passText)
        btnLog = v.findViewById(R.id.logBtn)
        btnReg = v.findViewById(R.id.regBtn)
        btnRecu = v.findViewById(R.id.btnRecuMail)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnReg.setOnClickListener {

            if (email.text.isNotEmpty() && pass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email.text.toString(),
                    pass.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        userMailLogin = email.text.toString()
                        vm.registerOK(v)
                    } else {
                        vm.registerFail(v)
                    }
                }
            } else{
                vm.registerFail(v)
            }
        }

        btnLog.setOnClickListener {
            if (email.text.isNotEmpty() && pass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    email.text.toString(),
                    pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        userMailLogin = email.text.toString()
                        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                        v.findNavController().navigate(action)
                    } else {
                        vm.loginFail(v)
                    }
                }
            }
        }

        btnRecu.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRecuMailFragment()
            v.findNavController().navigate(action)
        }
    }


}