package com.example.crudsqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListProductActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        recycler = findViewById(R.id.rv_list_product)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var sqLiteHelper = SQLiteHelper(this)

        val productList = sqLiteHelper.getAllProductos()
        productList.forEach {
            Log.i(ContentValues.TAG,"${it.nomprod}")
        }



        var product1: ProductModel = ProductModel(1, "Leche Gloria", 2.5, 3, 5)
        var product2: ProductModel = ProductModel(2, "Aceite Primor", 12.0, 50, 5)
        var product3: ProductModel = ProductModel(3, "Webos xd", 2.0, 6, 5)
        var product4: ProductModel = ProductModel(1, "Leche Gloria", 2.5, 3, 5)
        var product5: ProductModel = ProductModel(2, "Aceite Primor", 12.0, 50, 5)
        var product6: ProductModel = ProductModel(3, "Webos xd", 2.0, 6, 5)
        var product7: ProductModel = ProductModel(1, "Leche Gloria", 2.5, 3, 5)
        var product8: ProductModel = ProductModel(2, "Aceite Primor", 12.0, 50, 5)
        var product9: ProductModel = ProductModel(3, "Webos xd", 2.0, 6, 5)
        var product10: ProductModel = ProductModel(1, "Leche Gloria", 2.5, 3, 5)
        var product11: ProductModel = ProductModel(2, "Aceite Primor", 12.0, 50, 5)
        var product12: ProductModel = ProductModel(3, "Webos xd", 2.0, 6, 5)
        var product13: ProductModel = ProductModel(1, "Leche Gloria", 2.5, 3, 5)
        var product14: ProductModel = ProductModel(2, "Aceite Primor", 12.0, 50, 5)
        var product15: ProductModel = ProductModel(3, "Webos xd", 2.0, 6, 5)

        recycler.adapter = ProductAdapter(
            mutableListOf<ProductModel>(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6,
                product7,
                product8,
                product9,
                product10,
                product11,
                product12,
                product13,
                product14,
                product15
            )
        )
    }
}
