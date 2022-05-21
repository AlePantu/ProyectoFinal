package com.example.proyectofinal.viewmodels

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.google.firebase.firestore.FirebaseFirestore

class BeachViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()
    private lateinit var nameView : TextView

    fun showDataBeach(idPlaya: String, v: View) {

        nameView = v.findViewById(R.id.nameBeach)

        db.collection("dtis").document(idPlaya).get().addOnSuccessListener {

            nameView.text = it.get("nombre").toString()
            //beachTemp.text = it.get("temperatura").toString()
           // beachCrowd.text = it.get("aforo").toString()


        }

    }

}