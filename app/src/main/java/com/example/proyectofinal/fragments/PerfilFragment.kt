package com.example.proyectofinal.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.PerfilViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

   private lateinit var v : View

   private lateinit var userMail : TextView

   private lateinit var nameText : TextView
   private lateinit var apellidoText : TextView
   private lateinit var saveBtn : Button

    private lateinit var viewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_perfil, container, false)

        userMail = v.findViewById(R.id.textView2)
        nameText = v.findViewById(R.id.nameTextView)
        apellidoText = v.findViewById(R.id.apeText)
        saveBtn = v.findViewById(R.id.guardarBtn)

        return v
    }

    override fun onStart() {
        super.onStart()

        userMail.text = userMailLogin

        saveBtn.setOnClickListener {

                db.collection("users").document(userMailLogin).collection("favs")
                    .document("fav1").set(
                    hashMapOf(
                        "id" to apellidoText.text.toString(),
                        "nombre" to nameText.text.toString()
                    )
                )



        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilViewModel::class.java)
        // TODO: Use the ViewModel
    }

}