package com.example.proyectofinal.viewmodels

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.fragments.HomeFragment
import com.google.firebase.firestore.FirebaseFirestore
import me.tankery.lib.circularseekbar.CircularSeekBar

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var mailText : TextView

    private lateinit var beachName : TextView
    private lateinit var pcAforo : CircularSeekBar
    private lateinit var aforoView: TextView

    private lateinit var pcTemp : CircularSeekBar
    private lateinit var tempView: TextView

    private lateinit var pcPark : CircularSeekBar
    private lateinit var parkView: TextView

    private var aforo : Float = 0F
    private var temp : Float = 0F
    private var park : Float = 0F

    private lateinit var nameUser : String


    fun showData(dti : String , v : View) {

       beachName = v.findViewById(R.id.nameBeachView)
        aforoView = v.findViewById(R.id.aforoTextView)
        pcAforo = v.findViewById(R.id.pcaforo)

        tempView = v.findViewById(R.id.tempTextView)
        pcTemp = v.findViewById(R.id.pctemp)

        parkView = v.findViewById(R.id.parkTextView)
        pcPark = v.findViewById(R.id.pcpark)


        db.collection("dtis").document(dti).get().addOnSuccessListener {
            pcAforo.max = (it.get("maxAforo").toString()).toFloat()
            beachName.text = it.get("nombre").toString()
            aforo = (it.get("aforo").toString()).toFloat()
            temp =  (it.get("temperatura").toString()).toFloat()

            pcPark.max = (it.get("maxPark").toString()).toFloat()
            park = (it.get("parking").toString()).toFloat()

            aforoView.text = it.get("aforo").toString()
            pcAforo.progress = aforo
            tempView.text = it.get("temperatura").toString()
            pcTemp.progress = temp
            parkView.text = it.get("parking").toString()
            pcPark.progress = park



        }
    }

    fun userData(v: View) {

        mailText = v.findViewById(R.id.textView)

        db.collection("users").document(UserRepository.userMailLogin).get().addOnSuccessListener {
            nameUser = it.get("nombre").toString()

            if (nameUser == "null"){
                mailText.text = "Completar datos"

            } else {
                mailText.text = nameUser
            }
        }

    }

    fun populateFavs() {
        db.collection("users").document(userMailLogin).get().addOnSuccessListener {
           listOfFavs = it.get("favs") as ArrayList<String>
        }
    }

}