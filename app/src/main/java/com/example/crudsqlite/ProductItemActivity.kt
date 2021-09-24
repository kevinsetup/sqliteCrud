package com.example.crudsqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductItemActivity : AppCompatActivity() {
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_item)

        deleteButton = findViewById(R.id.btn_product_item_delete)
        deleteButton.setOnClickListener {
            Log.w(ContentValues.TAG,"INgeniero quevin")
            MaterialAlertDialogBuilder(this
            )
                .setMessage(resources.getString(R.string.delete_dialog))
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    // Respond to positive button press
                }
                .show()
        }



    }
}