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

    private lateinit var pcTemp : CircularSeekBar
    private lateinit var tempView: TextView

    private lateinit var pcPark : CircularSeekBar
    private lateinit var parkView: TextView

    private var aforo : Float = 0F
    private var temp : Float = 0F
    private var park : Float = 0F


    fun showDataBeach(idPlaya: String, v: View) {

        nameView = v.findViewById(R.id.nameBeach)

        aforoView = v.findViewById(R.id.aforoTextView)

        pcAforo = v.findViewById(R.id.pcaforo)

        tempView = v.findViewById(R.id.tempTextView)
        pcTemp = v.findViewById(R.id.pctemp)

        parkView = v.findViewById(R.id.parkTextView)
        pcPark = v.findViewById(R.id.pcpark)

        db.collection("dtis").document(idPlaya).get().addOnSuccessListener {
            pcAforo.max = (it.get("maxAforo").toString()).toFloat()
            pcPark.max = (it.get("maxPark").toString()).toFloat()
            aforo = (it.get("aforo").toString()).toFloat()
            temp =  (it.get("temperatura").toString()).toFloat()
            park = (it.get("parking").toString()).toFloat()

            nameView.text = it.get("nombre").toString()
           aforoView.text = it.get("aforo").toString()
            tempView.text = it.get("temperatura").toString()
            parkView.text = it.get("parking").toString()
            pcAforo.progress = aforo
            pcTemp.progress = temp
            pcPark.progress = park
        }


    }

}