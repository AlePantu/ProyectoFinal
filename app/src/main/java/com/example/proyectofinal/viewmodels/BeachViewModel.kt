package com.example.proyectofinal.viewmodels

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.google.firebase.firestore.FieldValue
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

    private lateinit var imageFlag : ImageView
    private lateinit var flagView : TextView

    private var aforo : Float = 0F
    private var temp : Float = 0F
    private var park : Float = 0F

    private lateinit var bandera : String

    private lateinit var bAddToFavs : Button
    private lateinit var bRemoveFavs : Button


    fun showDataBeach(idPlaya: String, v: View) {

        nameView = v.findViewById(R.id.nameBeach)

        aforoView = v.findViewById(R.id.aforoTextView)

        pcAforo = v.findViewById(R.id.pcaforo)

        tempView = v.findViewById(R.id.tempTextView)
        pcTemp = v.findViewById(R.id.pctemp)

        parkView = v.findViewById(R.id.parkTextView)
        pcPark = v.findViewById(R.id.pcpark)

        imageFlag = v.findViewById(R.id.flagImage)
        flagView = v.findViewById(R.id.flagTextView)

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
            bandera = it.get("bandera").toString()


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
            pcAforo.progress = aforo
            pcTemp.progress = temp
            pcPark.progress = park
        }


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