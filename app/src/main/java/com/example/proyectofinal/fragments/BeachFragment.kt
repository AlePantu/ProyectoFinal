package com.example.proyectofinal.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.viewmodels.BeachViewModel



class BeachFragment : Fragment() {

    private lateinit var v : View

    private val vm: BeachViewModel by viewModels()
    private lateinit var idPlaya : String
    private lateinit var bAddToFav : Button
    private lateinit var bRemoveFav : Button
    private lateinit var btnMap : ImageView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_beach, container, false)

        bAddToFav = v.findViewById(R.id.btnAddFavoritos)
        bRemoveFav = v.findViewById(R.id.btnRemoveFavoritos)
        btnMap = v.findViewById(R.id.btnMap)



        return v
    }

    override fun onStart() {
        super.onStart()

     idPlaya = BeachFragmentArgs.fromBundle(requireArguments()).dti



      vm.showDataBeach(idPlaya , v)

        vm.showButtons(v, idPlaya)

        bAddToFav.setOnClickListener {

            if(!vm.esFavorito(idPlaya)){
                //AGREGAR DTI A FAV
                vm.addFavotite(idPlaya)
                vm.favAdded(v, requireContext())
            }else{
                vm.favInList(v, requireContext())
            }
        }

        bRemoveFav.setOnClickListener {

            if(vm.esFavorito(idPlaya)){
                vm.removeFavorite(idPlaya)
                vm.favRemoved(v, requireContext())
                activity?.onBackPressed()
            }else{
                vm.dtiNotInList(v, requireContext())
            }
        }


        btnMap.setOnClickListener {
            goMap(idPlaya , v )
        }

    }
    fun goMap(idPlaya: String , v: View) {

        Toast.makeText(v.context, "MAPS", Toast.LENGTH_SHORT).show()

        val playa = UserRepository.ListDti[idPlaya.toInt()]
        val latitud = playa.geopoint.latitud
        val longitud = playa.geopoint.longitud

        val gmmIntentUri = Uri.parse("geo:"+ latitud+"," +longitud+"?q=playa "+playa.nombre)

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        startActivity(mapIntent)

    }


}