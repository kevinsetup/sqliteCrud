package com.example.crudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListProductActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        recycler= findViewById(R.id.rv_list_product)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        var product1:ProductModel= ProductModel(1,"Leche Gloria",2.5,3,5)
        var product2:ProductModel= ProductModel(2,"Aceite Primor",12.0,50,5)
        var product3:ProductModel= ProductModel(3,"Webos xd",2.0,6,5)

        recycler.adapter=ProductAdapter(mutableListOf<ProductModel>(product1,product2,product3))
    }
    }
