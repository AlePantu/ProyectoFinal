package com.example.proyectofinal.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_beach, container, false)

        dtiDocument = v.findViewById(R.id.dtiDoc)



        return v
    }

    override fun onStart() {
        super.onStart()

     idPlaya = BeachFragmentArgs.fromBundle(requireArguments()).dti

      vm.showDataBeach(idPlaya , v)
    }









}