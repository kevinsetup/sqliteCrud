package com.example.crudsqlite

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoriaAdapter : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {
    private var catList: ArrayList<CategoriaModel> = ArrayList();

    fun addItems(items: ArrayList<CategoriaModel>) {
        this.catList = items;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_category_item, parent, false)
    )

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.bindView(catList[position]);
    }


    override fun getItemCount(): Int = catList.size


    class CategoriaViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.name);


        fun bindView(cat: CategoriaModel) {
            name.text = cat.name;
        }


    }
}