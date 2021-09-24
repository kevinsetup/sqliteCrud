package com.example.crudsqlite

import android.view.View;
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoriaAdapter : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(var view : View) : RecyclerView.CategoriaViewHolder(view){
            private var name = view.findViewById<TextView>(R.id.rv_list_product);



            fun bindView(cat : CategoriaModel){
            name.text = cat.name;
            }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}