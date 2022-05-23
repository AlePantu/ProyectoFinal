package com.example.proyectofinal.fragments


import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


    private lateinit var v : View
    private val vm : HomeViewModel by viewModels()

    private val db = FirebaseFirestore.getInstance()
    private var dtiNames  = arrayListOf<String>()
    private lateinit var mapBeach : MapView
    private lateinit var listPopupWindowButton : Button
    private lateinit var goBeachButton: Button
    private lateinit var listPopupWindow: ListPopupWindow
    private var dtiDocument : String = "1"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.populateFavs()

        db.collection("dtis").get().addOnSuccessListener { miList ->
            for (dti in miList) {
                dtiNames.add(dti.get("nombre") as String)
            }
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

        vm.showData(dtiDocument , v)
        return v
    }

    override fun onStart() {
        super.onStart()

        vm.userData(v)



// Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, dtiNames)
        listPopupWindow.setAdapter(adapter)

// Set list popup's item click listener
      listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            userBeachSelect = dtiNames[position]
            dtiDocument = (position+1).toString()

            vm.showData( dtiDocument ,v)

            // Dismiss popup.
            listPopupWindow.dismiss()
             }

// Show list popup window on button click.
            listPopupWindowButton.setOnClickListener { listPopupWindow.show() }


        goBeachButton.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToBeachFragment(dtiDocument)
            v.findNavController().navigate(action)

        }
    }


}




