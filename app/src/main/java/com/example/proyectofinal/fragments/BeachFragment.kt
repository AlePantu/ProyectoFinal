package com.example.proyectofinal.fragments

import androidx.lifecycle.ViewModelProvider
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class BeachFragment : Fragment() {

    private lateinit var v : View

    private val vm: BeachViewModel by viewModels()

    private lateinit var dtiDocument : TextView

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_beach, container, false)

        dtiDocument = v.findViewById(R.id.dtiDoc)
        mapView = v.findViewById(R.id.mapView)

        return v
    }

    override fun onStart() {
        super.onStart()

        dtiDocument.text = BeachFragmentArgs.fromBundle(requireArguments()).dti

        mapView.getMapAsync {
            it.addMarker(MarkerOptions().position( LatLng(0.0 , 0.0)).title("Marker"))
        }
    }



}