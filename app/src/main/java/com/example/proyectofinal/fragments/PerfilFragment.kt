package com.example.proyectofinal.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController

import com.example.proyectofinal.R
import com.example.proyectofinal.viewmodels.PerfilViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {


   private lateinit var v : View


   private lateinit var editBtn : Button


    private val vm: PerfilViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_perfil, container, false)

                editBtn = v.findViewById(R.id.editBtn)

        return v
    }

    override fun onStart() {
        super.onStart()

        vm.showData(v)

        editBtn.setOnClickListener {

            val action = PerfilFragmentDirections.actionPerfilFragmentToEditProfileFragment()
            v.findNavController().navigate(action)

        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}