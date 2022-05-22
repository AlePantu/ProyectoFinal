package com.example.proyectofinal.viewmodels

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.FloatValue
import me.tankery.lib.circularseekbar.CircularSeekBar
import kotlin.properties.Delegates

class BeachViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()
    private lateinit var nameView : TextView
    private lateinit var pcAforo : CircularSeekBar
    private lateinit var aforoView: TextView

    private var aforo : Float = 0F


    fun showDataBeach(idPlaya: String, v: View) {

        nameView = v.findViewById(R.id.nameBeach)

        aforoView = v.findViewById(R.id.aforoTextView)

        pcAforo = v.findViewById(R.id.pcaforo)

        db.collection("dtis").document(idPlaya).get().addOnSuccessListener {
            pcAforo.max = 500F
            aforo = (it.get("aforo").toString()).toFloat()



            nameView.text = it.get("nombre").toString()
            //beachTemp.text = it.get("temperatura").toString()
           aforoView.text = aforo.toString()
            pcAforo.progress = aforo
        }


    }

}