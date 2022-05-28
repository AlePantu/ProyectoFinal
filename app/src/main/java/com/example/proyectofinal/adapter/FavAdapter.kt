package com.example.proyectofinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.entities.UserRepository.ListDti
import com.example.proyectofinal.entities.UserRepository.listOfFavs
import com.google.firebase.firestore.FirebaseFirestore

class FavAdapter(

    var newFavs : MutableList<String>,
    var onClick: (Int) -> Unit

) : RecyclerView.Adapter<FavAdapter.FavHolder>() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var nombre: String

    class FavHolder(v : View) : RecyclerView.ViewHolder(v){

        var view: View

        init{
            this.view = v
        }

        fun setName (name: String){
            var txtName : TextView = view.findViewById( R.id.txtItemName)
            txtName.text = name
        }

        fun getCard () : CardView {
            return view.findViewById(R.id.card)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favoritos, parent, false)
        return (FavHolder(view))
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {

        var fav = listOfFavs[position]

        var dti = ListDti[fav.toInt()]
        holder.setName(dti.nombre)

        holder.getCard().setOnClickListener{
            onClick(position)

        }
    }

    override fun getItemCount(): Int {
        return listOfFavs.size
    }


}