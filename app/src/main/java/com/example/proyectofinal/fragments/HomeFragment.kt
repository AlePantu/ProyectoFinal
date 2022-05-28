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
import com.example.proyectofinal.entities.APIService
import com.example.proyectofinal.entities.Dti
import com.example.proyectofinal.entities.RestEngine
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.userBeachSelect
import com.example.proyectofinal.entities.UserRepository.userMailLogin
import com.example.proyectofinal.viewmodels.HomeViewModel
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    private lateinit var playa : String

    private var ListDtiNombres = mutableListOf<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.populateFavs()
        callServiceGetDti()

       /* db.collection("dtis").get().addOnSuccessListener { miList ->
            for (dti in miList) {
                dtiNames.add(dti.get("nombre") as String)
            }
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       v =inflater.inflate(R.layout.fragment_home, container, false)


        listPopupWindowButton = v.findViewById(R.id.list_popup_button)
        goBeachButton = v.findViewById(R.id.goBeachBtn)
        listPopupWindow = ListPopupWindow(requireContext(), null, androidx.transition.R.attr.listPopupWindowStyle)

        //vm.showData(dtiDocument , v)
        return v
    }


    override fun onStart() {
        super.onStart()

      // vm.userData(v)

// Set button as the list popup's anchor
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, ListDtiNombres)
        listPopupWindow.setAdapter(adapter)

// Set list popup's item click listener
      listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            // Respond to list popup window item click.

          // playa = dtiNames[position]

         // Toast.makeText(context, playa, Toast.LENGTH_SHORT).show()

     //dtiDocument = vm.searchId(playa , v)


         // vm.showData( dtiDocument ,v)
          vm.showData2(position , v)
          dtiDocument = position.toString()

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

    private fun callServiceGetDti() {
        val dtiService : APIService = RestEngine.getRetrofitDtis().create(APIService::class.java)
        val result : Call<List<Dti>> = dtiService.getDtiList()

        result.enqueue(object : Callback<List<Dti>> {
            override fun onResponse(
                call: Call<List<Dti>>,
                response: Response<List<Dti>>
            ) {

                val r = response.body()
                if (r != null) {
                    ListDti  = r
                }

                Toast.makeText(requireContext(), "DTIs Cargados", Toast.LENGTH_SHORT).show()
                getDtiNames(ListDti)
            }
            override fun onFailure(call: Call<List<Dti>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en lectura", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDtiNames(list: List<Dti>): MutableList<String> {

        for (l in list){
            ListDtiNombres.addAll(listOf(l.nombre))
        }
        return ListDtiNombres
    }


}




