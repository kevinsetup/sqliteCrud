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
            Log.i(ContentValues.TAG, "${it.nomprod}")
        }


        recycler.adapter = ProductAdapter(
            productList
        )
    }
}
