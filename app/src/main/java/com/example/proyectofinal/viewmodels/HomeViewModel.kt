package com.example.proyectofinal.viewmodels

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import me.tankery.lib.circularseekbar.CircularSeekBar
import kotlin.concurrent.timerTask

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    //private lateinit var mailText : TextView

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

   // private lateinit var nameUser : String

    private val dtiNames = arrayListOf<String>()


   /* fun showData(dti : String , v : View) {

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

            aforoView.text = it.get("aforo").toString() + " Personas"
            pcAforo.progress = aforo
            tempView.text = it.get("temperatura").toString()+"°"
            pcTemp.progress = temp
            parkView.text = it.get("parking").toString()+" Ocupados"
            pcPark.progress = park

        }
    }*/

  /*  fun userData(v: View) {

        mailText = v.findViewById(R.id.textView)

        db.collection("users").document(UserRepository.userMailLogin).get().addOnSuccessListener {
            nameUser = it.get("nombre").toString()

            if (nameUser == "null"){
                mailText.text = "Completar datos"

            } else {
                mailText.text = nameUser
            }
        }

    }*/

    fun populateFavs() {
        db.collection("users").document(userMailLogin).get().addOnSuccessListener {
           listOfFavs = it.get("favs") as ArrayList<String>
        }
    }

  /*suspend fun searchId(playa: String , v: View): String {
        var id : String=""
        var nombrePlaya = ""

        db.collection("dtis").whereEqualTo("nombre", playa).get().addOnSuccessListener {
                documents ->
            for (document in documents){
                nombrePlaya =document.get("nombre").toString()
                if (nombrePlaya == playa){
                    id = document.get("id").toString()
                }
            }
        }
       return id



    }*/

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

          //aforoView.text = dti.aforo + " Personas"
         // pcAforo.progress = aforo
          tempView.text = dti.temperatura+"°"
         pcTemp.progress = temp
          //parkView.text = dti.parking+" Ocupados"
         // pcPark.progress = park

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


}


