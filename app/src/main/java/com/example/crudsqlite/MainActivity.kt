package com.example.crudsqlite

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var nameText: TextInputEditText
    lateinit var priceText: TextInputEditText
    lateinit var stockText: TextInputEditText
    lateinit var addButton: Button
    lateinit var listButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.tiet_main_product_name)
        priceText = findViewById(R.id.tiet_main_product_price)
        stockText = findViewById(R.id.tiet_main_product_stock)
        addButton = findViewById(R.id.btn_main_add_product)
        listButton = findViewById(R.id.btn_main_list_product)

        addButton.setOnClickListener {
            validateForm(
                nameText.text.toString(),
                priceText.text.toString(),
                stockText.text.toString()
            )


        }

        listButton.setOnClickListener {
            startActivity(Intent(applicationContext, ListProductActivity::class.java))
        }


    }

    fun validateForm(name: String, price: String, stock: String) {
        if (name.isBlank() && price.isBlank() && stock.isBlank()) {
            Toast.makeText(applicationContext, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }
        var sqLiteHelper: SQLiteHelper = SQLiteHelper(applicationContext)
        var successCode =
            sqLiteHelper.insertProducto(ProductModel(1, name, price.toDouble(), stock.toInt(), 2))
        Log.i(ContentValues.TAG, "$successCode")
        if (successCode != (-1).toLong()) {
            Toast.makeText(applicationContext, "Sucessfully Inserted Row", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(applicationContext, "que asiendi", Toast.LENGTH_SHORT).show()
        }

    }

}