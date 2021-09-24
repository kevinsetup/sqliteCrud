package com.example.crudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductItemActivity : AppCompatActivity() {
    lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_item)

        recycler= findViewById(R.id.rv_list_product)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        var product1:ProductModel= ProductModel(1,"Leche Gloria",2.5,3,5)
        var product2:ProductModel= ProductModel(1,"Aceite Primor",2.5,3,5)
        var product3:ProductModel= ProductModel(1,"Webos xd",2.5,3,5)

        recycler.adapter=ProductAdapter(mutableListOf<ProductModel>(product1,product2,product3))

    }
}