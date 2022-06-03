package com.example.proyectofinal.fragments


import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.APIService
import com.example.proyectofinal.entities.Dti
import com.example.proyectofinal.entities.RestEngine
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.ListDtiNombres
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.entities.UserRepository.userLatitud
import com.example.proyectofinal.entities.UserRepository.userLongitud
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


    private lateinit var v : View
    private val vm : HomeViewModel by viewModels()
    private lateinit var listPopupWindowButton : Button
    private lateinit var goBeachButton: Button
    private lateinit var listPopupWindow: ListPopupWindow
    private var dtiDocument : String = "0"
    private lateinit var goForm : Button
    private lateinit var goAbout : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { vm.populateFavs() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)


        listPopupWindowButton = v.findViewById(R.id.list_popup_button)
        goBeachButton = v.findViewById(R.id.goBeachBtn)
        listPopupWindow = ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        goForm = v.findViewById(R.id.btnForm)
        goAbout = v.findViewById(R.id.btnAbout)

        return v
    }


    override fun onStart() {
        super.onStart()

        //Nos va a mostrar el DTI que se encuentra mas cerca a nuestra posicion por Geolocalizacion
        dtiCercano()

        // Ajusta el boton de la lista
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, ListDtiNombres)
        listPopupWindow.setAdapter(adapter)

        //El Listener cuando elegimos una opcion
      listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->

          //Responde cuando elegimos un boton de la lista
          vm.showData(position , v)
          dtiDocument = position.toString()
          userBeachSelect = position.toString()

            // Dismiss popup.
            listPopupWindow.dismiss()
             }

            // Muestra la lista
            listPopupWindowButton.setOnClickListener { listPopupWindow.show() }


        goBeachButton.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToBeachFragment(dtiDocument)
            v.findNavController().navigate(action)
        }

        goForm.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFormularioFragment()
            v.findNavController().navigate(action)

        }

        goAbout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToContactoFragment()
            v.findNavController().navigate(action)
        }

    }

    private  fun dtiCercano(){

        var dtiCerca = 0
        var distEntreDTIyUser = 9999999999999999F
        var position = 0

        for (dti in ListDti){

            val locationA = Location("punto A")

            locationA.latitude = userLatitud.toDouble()
            locationA.longitude = userLongitud.toDouble()

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
       vm.showData(dtiCerca , v)
    }


}




