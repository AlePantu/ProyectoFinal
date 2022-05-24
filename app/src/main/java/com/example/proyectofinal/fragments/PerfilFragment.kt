package com.example.proyectofinal.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels

import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.PerfilViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

   private lateinit var v : View


   private lateinit var nombreText : TextView
   private lateinit var apellidoText : TextView
   private lateinit var nameText : TextView
   private lateinit var lastNameText : TextView
   private lateinit var saveBtn : Button
   private lateinit var name : String
   private lateinit var last : String

    private val vm: PerfilViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_perfil, container, false)


        nameText = v.findViewById(R.id.nameTextView)
        lastNameText = v.findViewById(R.id.apeText)
        saveBtn = v.findViewById(R.id.guardarBtn)

        nombreText = v.findViewById(R.id.nameInText)
        apellidoText = v.findViewById(R.id.lastInText)



        val docRef = db.collection("users").document(userMailLogin)

        docRef.get().addOnCompleteListener{ document ->
            if (document != null) {

                name = document.result.get("nombre").toString()
                last = document.result.get("apellido").toString()

                if (name != "null"){
                    nombreText.text = name
                } else {
                    nombreText.text = ""
                }
                if(last != "null") {
                    apellidoText.text = last
                } else {
                    apellidoText.text = ""
                }

            }

        }

        return v
    }

    override fun onStart() {
        super.onStart()



        saveBtn.setOnClickListener {

                db.collection("users").document(userMailLogin).set(
                    hashMapOf(
                        "apellido" to lastNameText.text.toString(),
                        "nombre" to nameText.text.toString()
                    ) ,
                    SetOptions.merge()
                )



        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}