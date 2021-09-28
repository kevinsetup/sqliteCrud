package com.example.crudsqlite

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class CreateProductActivity : AppCompatActivity() {

    lateinit var nameText: TextInputEditText
    lateinit var priceText: TextInputEditText
    lateinit var stockText: TextInputEditText
    lateinit var addButton: Button
    lateinit var listButton: Button
    lateinit var categoryDropdown: AutoCompleteTextView
    private var idcategoria: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        nameText = findViewById(R.id.tiet_create_product_name)
        priceText = findViewById(R.id.tiet_create_product_price)
        stockText = findViewById(R.id.tiet_create_product_stock)
        addButton = findViewById(R.id.btn_create_product_add)
        listButton = findViewById(R.id.btn_create_product_list)

        categoryDropdown = findViewById(R.id.actv_create_product_category)


        val sqLiteHelper: SQLiteHelper = SQLiteHelper(applicationContext)
        val items: ArrayList<CategoriaModel> = sqLiteHelper.getAllCategorias()
        val adapter = ArrayAdapter(applicationContext, R.layout.dropdown_product_item, items)

        categoryDropdown.setAdapter(adapter)
        addButton.setOnClickListener {
            validateForm(
                nameText.text.toString(),
                priceText.text.toString(),
                stockText.text.toString(),
                idcategoria
            )

        }

        listButton.setOnClickListener {
            startActivity(Intent(applicationContext, ListProductActivity::class.java))
        }
        categoryDropdown.setOnItemClickListener { parent, view, position, id ->
            val item: CategoriaModel = categoryDropdown.adapter.getItem(position) as CategoriaModel
            idcategoria = item.id
        }


    }

    fun cleanForm() {
        nameText.setText("")
        priceText.setText("")
        stockText.setText("")
    }

    fun validateForm(name: String, price: String, stock: String, idcategoria: Int) {
        if (name.isBlank() && price.isBlank() && stock.isBlank() && idcategoria == -1) {
            Log.i(ContentValues.TAG, "${categoryDropdown.editableText}")
            Toast.makeText(applicationContext, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }
        var sqLiteHelper = SQLiteHelper(applicationContext)
        var successCode =
            sqLiteHelper.insertProducto(
                ProductModel(
                    1,
                    name,
                    price.toDouble(),
                    stock.toInt(),
                    idcategoria
                )
            )
        Log.i(ContentValues.TAG, "$successCode")
        if (successCode != (-1).toLong()) {
            Toast.makeText(applicationContext, "Sucessfully Inserted Row", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(applicationContext, "que asiendi", Toast.LENGTH_SHORT).show()
        }

    }
}