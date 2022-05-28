package com.example.proyectofinal.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.FloatValue
import me.tankery.lib.circularseekbar.CircularSeekBar
import kotlin.properties.Delegates
@SuppressLint("StaticFieldLeak")
class BeachViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()

    private lateinit var nameView : TextView
    private lateinit var pcAforo : CircularSeekBar
    private lateinit var aforoView: TextView

    private lateinit var pcTemp : CircularSeekBar
    private lateinit var tempView: TextView

    private lateinit var pcPark : CircularSeekBar
    private lateinit var parkView: TextView

    private lateinit var imageFlag : ImageView
    private lateinit var flagView : TextView

    private lateinit var pcUvs : CircularSeekBar
    private lateinit var uvView: TextView

    private lateinit var dirV : TextView
    private lateinit var velV : TextView
    private lateinit var altO : TextView

    private var aforo : Float = 0F
    private var temp : Float = 0F
    private var park : Float = 0F
    private var uvs : Float = 0F

    private lateinit var bandera : String
    private lateinit var rayosUv : String

    private lateinit var bAddToFavs : Button
    private lateinit var bRemoveFavs : Button


    fun showDataBeach(idPlaya: String, v: View) {

        var posDti = ListDti[idPlaya.toInt()]

        nameView = v.findViewById(R.id.nameBeach)

        aforoView = v.findViewById(R.id.aforoTextView)

        pcAforo = v.findViewById(R.id.pcaforo)

        tempView = v.findViewById(R.id.tempTextView)
        pcTemp = v.findViewById(R.id.pctemp)

        parkView = v.findViewById(R.id.parkTextView)
        pcPark = v.findViewById(R.id.pcpark)

        imageFlag = v.findViewById(R.id.flagImage)
        flagView = v.findViewById(R.id.flagTextView)

        uvView = v.findViewById(R.id.uvRaystextView)
        pcUvs = v.findViewById(R.id.pcUv)

        altO = v.findViewById(R.id.altOlasTV)
        velV = v.findViewById(R.id.windVelTV)
        dirV = v.findViewById(R.id.windDirTV)


            pcAforo.max = posDti.maxAforo.toFloat()
            pcPark.max = posDti.maxPark.toFloat()
            aforo = posDti.aforo.toFloat()
            temp =  posDti.temperatura.toFloat()
            park = posDti.parking.toFloat()
            uvs = posDti.uv.toFloat()

            nameView.text = posDti.nombre
           aforoView.text = posDti.aforo+ " Personas"
            tempView.text = posDti.temperatura+"°"
            parkView.text = posDti.parking+" Ocupados"
            bandera = posDti.bandera
            rayosUv = posDti.uv

            altO.text = posDti.altOla+ "mts"
            velV.text = posDti.velViento + "km/h"
            dirV.text = posDti.dirViento.uppercase()


            when(bandera){
                "bueno" -> {
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.bueno))
                    flagView.text = "BUENO"
                }
                "dudoso" ->{
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.dudoso))
                    flagView.text = "DUDOSO"
                }
                "peligroso" -> {
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.peligroso))
                    flagView.text = "PELIGROSO"
                }
                "niñoper" -> {
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.ninoextr))
                    flagView.text = "NIÑO PERDIDO"
                }
                "prohibido" -> {
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.prohibido))
                    flagView.text = "PROHIBIDO BAÑARSE"
                }
                "rayos" -> {
                    imageFlag.setImageDrawable(v.context.getDrawable(R.drawable.rayos))
                    flagView.text = "RAYOS - EVACUAR"
                }
            }

            when(rayosUv){

                "1" -> uvView.text = "1 - Bajo"
                "2" -> uvView.text = "2 - Bajo"
                "3" -> uvView.text = "3 - Moderado"
                "4" -> uvView.text = "4 - Moderado"
                "5" -> uvView.text = "5 - Moderado"
                "6" -> uvView.text = "6 - Alto"
                "7" -> uvView.text = "7 - Alto"
                "8" -> uvView.text = "8 - Muy Alto"
                "9" -> uvView.text = "9 - Muy Alto"
                "10"-> uvView.text = "10 - Muy Alto"

            }
            pcAforo.progress = aforo
            pcTemp.progress = temp
            pcPark.progress = park
            pcUvs.progress = uvs



    }

    fun showButtons(v: View, docDti: String) {
        bAddToFavs = v.findViewById(R.id.btnAddFavoritos)
        bRemoveFavs = v.findViewById(R.id.btnRemoveFavoritos)

        if(!esFavorito(docDti)) {
            bRemoveFavs.visibility = View.INVISIBLE
        }else{
            bAddToFavs.visibility = View.INVISIBLE
        }
    }

    fun esFavorito(docDti: String): Boolean {
        val x = listOfFavs.find { f -> f == docDti }
        return x != null
    }

    fun dtiNotInList(v: View, context : Context) {

        val text = "Dti no se encuentra en lista de favoritos"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    fun favRemoved(v : View, context : Context) {
        //Snackbar.make(v, "Dti ha sido eliminado de su lista de favoritos", Snackbar.LENGTH_SHORT).show()
        val text = "Dti ha sido eliminado de su lista de favoritos"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    fun removeFavorite(x: String) {
        var favoritos = db.collection("users").document(userMailLogin)
        favoritos.update("favs", FieldValue.arrayRemove(x))
    }

    fun addFavotite(x: String) {
        var favoritos = db.collection("users").document(userMailLogin)
        favoritos.update("favs", FieldValue.arrayUnion(x))

    }

    fun favAdded(v : View, context : Context) {

        val text = "Dti agregado correctamente a su lista de favoritos"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    fun favInList(v : View, context : Context) {

        val text = "El Dti ya se encuentra en su lista de favoritos"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }



}