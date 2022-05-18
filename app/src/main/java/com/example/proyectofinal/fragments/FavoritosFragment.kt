package com.example.proyectofinal.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.FavoritosViewModel

@Suppress("DEPRECATION")
class FavoritosFragment : Fragment() {

   private lateinit var v : View
   private lateinit var userMail : TextView

    private lateinit var viewModel: FavoritosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_favoritos, container, false)

        userMail = v.findViewById(R.id.textView3)
        return v
    }

    override fun onStart() {
        super.onStart()

        userMail.text = userMailLogin
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}