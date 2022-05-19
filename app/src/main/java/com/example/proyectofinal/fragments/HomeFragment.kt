package com.example.proyectofinal.fragments


import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    var listaMeses = listOf("enero","febrero","marzo","abril","mayo","junio","julio",
        "agosto","septiembre","octubre","noviembre","diciembre")



    private lateinit var v : View



    private lateinit var viewModel: HomeViewModel

    private lateinit var mailUser: String
    private lateinit var mailText : TextView
    private lateinit var spinnerDTI : Spinner
    private val db = FirebaseFirestore.getInstance()
    val dtiNames  = arrayListOf<String>()
    private lateinit var selectedBeach : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)

        mailText = v.findViewById(R.id.textView)
        spinnerDTI = v.findViewById(R.id.spinnerDTI)
        selectedBeach = v.findViewById(R.id.selecTextView)

        db.collection("dtis").get().addOnSuccessListener { miList ->
            for(dti in miList) {
                dtiNames.add(dti.get("nombre") as String)
            }

            populateSpinner(spinnerDTI,dtiNames,context!!)

        }


        return v
    }

    override fun onStart() {
        super.onStart()


        mailText.text = userMailLogin

        spinnerDTI.setSelection(0, false) // evita la primer falsa entrada
        spinnerDTI.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

               selectedBeach.text = dtiNames[position]

                Snackbar.make(v, dtiNames[position], Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {

            R.id.action_add ->Snackbar.make(v, "add", Snackbar.LENGTH_SHORT).show()

            R.id.action_fav ->Snackbar.make(v, "fav", Snackbar.LENGTH_SHORT).show()

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun populateSpinner (spinner: Spinner, list : List<String>, context : Context)
    {
        //   val aa = ArrayAdapter( context!!, android.R.layout.simple_spinner_item, list)
        val aa = ArrayAdapter(context,android.R.layout.simple_spinner_item, list)

        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.adapter = aa
    }

}