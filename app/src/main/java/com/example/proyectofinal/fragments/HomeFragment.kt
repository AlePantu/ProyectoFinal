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
    private val vm : HomeViewModel by viewModels()
    private lateinit var mailText : TextView
    private val db = FirebaseFirestore.getInstance()
    private var dtiNames  = arrayListOf<String>()
    private lateinit var mapBeach : MapView
    private lateinit var listPopupWindowButton : Button
    private lateinit var listPopupWindow: ListPopupWindow

   // private lateinit var beachTemp : TextView
    //private lateinit var beachName : TextView
   // private lateinit var beachCrowd : TextView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)

        mailText = v.findViewById(R.id.textView)
        listPopupWindowButton = v.findViewById(R.id.list_popup_button)
        listPopupWindow = ListPopupWindow(this.context!!, null, androidx.transition.R.attr.listPopupWindowStyle)

        //beachName = v.findViewById(R.id.nameBeachView)
        //beachTemp = v.findViewById(R.id.tempBeachView)
       // beachCrowd = v.findViewById(R.id.crowdBeachView)

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
       // selectedBeach.text = userBeachSelect

// Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(context!!, R.layout.list_popup_window_item, dtiNames)
        listPopupWindow.setAdapter(adapter)

// Set list popup's item click listener
      listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

            userBeachSelect = dtiNames[position]


          val dtiDocument = (position+1).toString()

        vm.showData( dtiDocument ,v)

            Snackbar.make(v, dtiDocument, Snackbar.LENGTH_SHORT).show()
            // Dismiss popup.
            listPopupWindow.dismiss()
             }

// Show list popup window on button click.
            listPopupWindowButton.setOnClickListener { listPopupWindow.show() }
    }

   /* private fun showDtiData(dti : String){

        db.collection("dti").document(dti).get().addOnSuccessListener {
            temp.setText(it.get("temperatura") as String?)
            clima.setText(it.get("clima") as String?)
        }
    }*/
}