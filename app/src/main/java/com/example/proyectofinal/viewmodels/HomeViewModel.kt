package com.example.proyectofinal.viewmodels

import android.location.Location
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.dtiDocument
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import me.tankery.lib.circularseekbar.CircularSeekBar
import kotlin.concurrent.timerTask

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()


    private lateinit var beachName : TextView
    private lateinit var pcAforo : CircularSeekBar
    private lateinit var aforoView: TextView

    private lateinit var pcTemp : CircularSeekBar
    private lateinit var tempView: TextView

    private lateinit var pcPark : CircularSeekBar
    private lateinit var parkView: TextView

    private lateinit var aforo : String
    private var temp : Float = 0F
    private lateinit var park : String

    private val dtiNames = arrayListOf<String>()



    fun populateFavs() {
        db.collection("users").document(userMailLogin).get().addOnSuccessListener {
           listOfFavs = it.get("favs") as ArrayList<String>
        }
    }


    fun showData(pos: Int, v: View) {

        beachName = v.findViewById(R.id.nameBeachView)
        aforoView = v.findViewById(R.id.aforoTextView)
        pcAforo = v.findViewById(R.id.pcaforo)

        tempView = v.findViewById(R.id.tempTextView)
        pcTemp = v.findViewById(R.id.pctemp)

        parkView = v.findViewById(R.id.parkTextView)
        pcPark = v.findViewById(R.id.pcpark)

        var dti =  ListDti[pos]

           //pcAforo.max = dti.maxAforo.toFloat()
            beachName.text = dti.nombre
          aforo = dti.aforo
         temp =  dti.temperatura.toFloat()

          //pcPark.max = dti.maxPark.toFloat()
         park = dti.parking


          tempView.text = dti.temperatura+"°"
         pcTemp.progress = temp

        when(aforo){
            "bajo"-> {
                aforoView.text = "Bajo"
                pcAforo.progress = 25F
            }
            "medio"-> {
                aforoView.text = "Medio"
                pcAforo.progress = 50F
            }
            "alto"-> {
                aforoView.text = "Alto"
                pcAforo.progress = 75F
            }
            "lleno"-> {
                aforoView.text = "Lleno"
                pcAforo.progress = 100F
            }
        }

        when(park){
            "bajo"-> {
                parkView.text = "Bajo"
                pcPark.progress = 25F
            }
            "medio"-> {
                parkView.text = "Medio"
                pcPark.progress = 50F
            }
            "alto"-> {
                parkView.text = "Alto"
                pcPark.progress = 75F
            }
            "lleno"-> {
                parkView.text = "Lleno"
                pcPark.progress = 100F
            }
        }



    }

    fun cleanLogUser() {
        userMailLogin = ""
    }

        fun dtiCercano(v : View){

        var dtiCerca = 0
        var distEntreDTIyUser = 9999999999999999F
        var position = 0

        for (dti in ListDti){

            val locationA = Location("punto A")

            locationA.latitude = UserRepository.userLatitud.toDouble()
            locationA.longitude = UserRepository.userLongitud.toDouble()

            val locationB = Location("punto B")

            locationB.latitude = dti.geopoint.latitud.toDouble()
            locationB.longitude = dti.geopoint.longitud.toDouble()

            val distance = locationA.distanceTo(locationB)

            if (distance < distEntreDTIyUser ){
                dtiCerca = position
                distEntreDTIyUser = distance
            }
            position++
        }
        dtiDocument = dtiCerca.toString()
        showData(dtiCerca , v )
    }


}


