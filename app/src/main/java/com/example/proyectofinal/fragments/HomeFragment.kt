package com.example.proyectofinal.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin


import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var v : View

    private lateinit var viewModel: HomeViewModel

    private lateinit var mailUser: String
    private lateinit var mailText : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)

        mailText = v.findViewById(R.id.textView)



        return v
    }

    override fun onStart() {
        super.onStart()

        //mailUser = HomeFragmentArgs.fromBundle(requireArguments()).userMail
        mailText.text = userMailLogin

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}