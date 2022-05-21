package com.example.proyectofinal.viewmodels

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.fragments.HomeFragment
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var beachTemp : TextView
    private lateinit var beachName : TextView
    private lateinit var beachCrowd : TextView


    fun showData(dti : String , v : View) {

       beachName = v.findViewById(R.id.nameBeachView)
        beachTemp = v.findViewById(R.id.tempBeachView)
        beachCrowd = v.findViewById(R.id.crowdBeachView)


        db.collection("dtis").document(dti).get().addOnSuccessListener {

            beachName.text = it.get("nombre").toString()
            beachTemp.text = it.get("temperatura").toString()
            beachCrowd.text = it.get("aforo").toString()


        }
    }

}