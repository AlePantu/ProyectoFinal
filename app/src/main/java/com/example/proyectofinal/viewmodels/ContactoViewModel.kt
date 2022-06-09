package com.example.proyectofinal.viewmodels

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.Config
import com.example.proyectofinal.entities.Config.MAIL_CONTACTO
import com.example.proyectofinal.entities.UserRepository
import com.google.firebase.auth.FirebaseAuth

class ContactoViewModel : ViewModel() {

    private lateinit var consulta : TextView

    fun cleanLogUser() {
        UserRepository.userMailLogin = ""
    }

    fun sendMail(context : Context , v : View) {

        consulta = v.findViewById(R.id.consTextView)

        val mailto = "mailto:$MAIL_CONTACTO"

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT ,"Consulta")
        emailIntent.putExtra(Intent.EXTRA_TEXT ,consulta.text.toString())

        startActivity(context , emailIntent , null)

    }

    fun goCiudadesWeb(context: Context) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(Config.URL_CIUDADES)
        startActivity(context , i , null)
    }

    fun goRedditWeb(context: Context) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(Config.URL_REDDIT)
        startActivity(context , i , null)
    }

    fun dialog(context: Context , activity : Activity) {
        AlertDialog.Builder(context)
            .setMessage("Cerrar Aplicacion?")
            .setCancelable(false)
            .setPositiveButton("Aceptar") { dialog, whichButton ->
                FirebaseAuth.getInstance().signOut()
                cleanLogUser()
                activity.finish()
            }
            .setNegativeButton("Cancelar") { dialog, whichButton ->

            }
            .show()

    }

}