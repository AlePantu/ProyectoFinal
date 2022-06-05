package com.example.proyectofinal.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.ListDtiNombres
import com.example.proyectofinal.entities.UserRepository.dtiDocument
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.entities.UserRepository.userLatitud
import com.example.proyectofinal.entities.UserRepository.userLongitud
import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


    private lateinit var v : View
    private val vm : HomeViewModel by viewModels()
    private lateinit var listPopupWindowButton : Button
    private lateinit var goBeachButton: Button
    private lateinit var listPopupWindow: ListPopupWindow
    //private var dtiDocument : String = "0"
    private lateinit var goForm : Button
    private lateinit var goAbout : Button
    private lateinit var bOut : Button


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
        bOut = v.findViewById(R.id.btnLogout)

        return v
    }


    override fun onStart() {
        super.onStart()

        if(!userLatitud.isBlank() && !userLongitud.isBlank()){
        //Nos va a mostrar el DTI que se encuentra mas cerca a nuestra posicion por Geolocalizacion
        vm.dtiCercano(v)}
        else {
            vm.showData(dtiDocument.toInt() ,v )
            Toast.makeText(context , "No tiene activado Geolocalizacion" , Toast.LENGTH_SHORT).show()
        }

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

        bOut.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setMessage("Cerrar Sesión?")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { dialog, whichButton ->

                    FirebaseAuth.getInstance().signOut()
                    vm.cleanLogUser()
                    activity?.finish()

                }
                .setNegativeButton("Cancelar") { dialog, whichButton ->

                }
                .show()
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            AlertDialog.Builder(requireContext())
                .setMessage("Cerrar Sesión?")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { dialog, whichButton ->
                    FirebaseAuth.getInstance().signOut()
                    vm.cleanLogUser()
                    activity?.finish()
                }
                .setNegativeButton("Cancelar") { dialog, whichButton ->

                }
                .show()
        }

    }




}




