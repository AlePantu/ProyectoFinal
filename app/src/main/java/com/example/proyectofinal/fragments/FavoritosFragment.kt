package com.example.proyectofinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal.R
import com.example.proyectofinal.adapter.FavAdapter
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.example.proyectofinal.viewmodels.FavoritosViewModel

@Suppress("DEPRECATION")
class FavoritosFragment : Fragment() {

   private lateinit var v : View

   private val vm: FavoritosViewModel by viewModels()
    lateinit var adapter: FavAdapter
    private lateinit var pos : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_favoritos, container, false)
        vm.recyclerFavs = v.findViewById(R.id.recFav)

        return v
    }

    override fun onStart() {
        super.onStart()
        vm.recyclerFavs.setHasFixedSize(true)
        vm.recyclerFavs.layoutManager = LinearLayoutManager(context)

        adapter = FavAdapter(listOfFavs)  {position ->

            pos = listOfFavs[position]

            var action = FavoritosFragmentDirections.actionFavoritosFragmentToBeachFragment(pos)
            v.findNavController().navigate(action)

        }
        vm.recyclerFavs.adapter = adapter

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}