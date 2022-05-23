package com.example.proyectofinal.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.proyectofinal.R
import com.example.proyectofinal.viewmodels.BeachViewModel
import com.google.android.gms.maps.MapView
import me.tankery.lib.circularseekbar.CircularSeekBar


class BeachFragment : Fragment() {

    private lateinit var v : View

    private val vm: BeachViewModel by viewModels()

    private lateinit var dtiDocument : TextView

    private lateinit var idPlaya : String

    private lateinit var bAddToFav : Button
    private lateinit var bRemoveFav : Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_beach, container, false)

        dtiDocument = v.findViewById(R.id.dtiDoc)
        bAddToFav = v.findViewById(R.id.btnAddFavoritos)
        bRemoveFav = v.findViewById(R.id.btnRemoveFavoritos)



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
            }else{
                vm.dtiNotInList(v, requireContext())
            }
        }
    }









}