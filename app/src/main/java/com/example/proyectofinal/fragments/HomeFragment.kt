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
    private lateinit var vm : HomeViewModel
    private lateinit var mailText : TextView
    private val db = FirebaseFirestore.getInstance()
    private var dtiNames  = arrayListOf<String>()
    private lateinit var selectedBeach : TextView
    private lateinit var mapBeach : MapView
    private lateinit var listPopupWindowButton : Button
    private lateinit var listPopupWindow: ListPopupWindow


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)

        mailText = v.findViewById(R.id.textView)
        selectedBeach = v.findViewById(R.id.selecTextView)
        listPopupWindowButton = v.findViewById(R.id.list_popup_button)
        listPopupWindow = ListPopupWindow(this.context!!, null, androidx.transition.R.attr.listPopupWindowStyle)

        db.collection("dtis").get().addOnSuccessListener { miList ->
            for(dti in miList) {
                dtiNames.add(dti.get("nombre") as String)
            }
        }



        return v
    }

    override fun onStart() {
        super.onStart()

        mailText.text = userMailLogin
        selectedBeach.text = userBeachSelect

// Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(context!!, R.layout.list_popup_window_item, dtiNames)
        listPopupWindow.setAdapter(adapter)

// Set list popup's item click listener
      listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, view: View?, position: Int, id: Long ->
            // Respond to list popup window item click.

            userBeachSelect = dtiNames[position]
          selectedBeach.text = userBeachSelect

            Snackbar.make(v, dtiNames[position], Snackbar.LENGTH_SHORT).show()
            // Dismiss popup.
            listPopupWindow.dismiss()
             }

// Show list popup window on button click.
            listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }
    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {

            R.id.action_add ->Snackbar.make(v, "add", Snackbar.LENGTH_SHORT).show()

            R.id.action_fav ->Snackbar.make(v, "fav", Snackbar.LENGTH_SHORT).show()

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }*/
}