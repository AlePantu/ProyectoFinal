package com.example.proyectofinal.viewmodels

import android.telephony.ims.ImsMmTelManager
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditProfileViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var nameText : TextView
    private lateinit var lastNameText : TextView
    private lateinit var telText : TextView
    private lateinit var provText : TextView
    private lateinit var cityText : TextView

    private lateinit var name : String
    private lateinit var last : String
    private lateinit var tel : String
    private lateinit var prov : String
    private lateinit var ciu : String


    fun saveData(v: View) {

        nameText = v.findViewById(R.id.nameEdit)
        lastNameText = v.findViewById(R.id.lastEdit)
        telText = v.findViewById(R.id.telEdit)
        provText = v.findViewById(R.id.provEdit)
        cityText = v.findViewById(R.id.cityEdit)

        db.collection("users").document(UserRepository.userMailLogin).set(
            hashMapOf(
                "apellido" to lastNameText.text.toString(),
                "nombre" to nameText.text.toString(),
                "telefono" to telText.text.toString(),
                "provincia" to provText.text.toString(),
                "ciudad" to cityText.text.toString()
            ) ,
            SetOptions.merge()
        )

    }

    fun showData(v: View) {

        nameText = v.findViewById(R.id.nameEdit)
        lastNameText = v.findViewById(R.id.lastEdit)
        telText = v.findViewById(R.id.telEdit)
        provText = v.findViewById(R.id.provEdit)
        cityText = v.findViewById(R.id.cityEdit)

        val docRef = db.collection("users").document(UserRepository.userMailLogin)

        docRef.get().addOnCompleteListener{ document ->
            if (document != null) {

                name = document.result.get("nombre").toString()
                last = document.result.get("apellido").toString()
                tel = document.result.get("telefono").toString()
                prov = document.result.get("provincia").toString()
                ciu = document.result.get("ciudad").toString()

                if (name != "null"){
                    nameText.text = name
                } else {
                    nameText.text = ""
                }
                if(last != "null") {
                    lastNameText.text = last
                } else {
                    lastNameText.text = ""
                }
                if(tel != "null") {
                    telText.text = tel
                } else {
                    telText.text = ""
                }
                if(prov != "null") {
                    provText.text = prov
                } else {
                    provText.text = ""
                }
                if(ciu != "null") {
                    cityText.text = ciu
                } else {
                    cityText.text = ""
                }
            }

        }

    }

}