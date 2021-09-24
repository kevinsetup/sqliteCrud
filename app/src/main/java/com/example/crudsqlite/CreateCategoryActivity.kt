package com.example.crudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var edName: EditText
    private lateinit var sqliteHelper: SQLiteHelper;
    private lateinit var recyclerView: RecyclerView
    private var adapter: CategoriaAdapter? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        initRecyclerView();

        sqliteHelper = SQLiteHelper(this);

        edName = findViewById(R.id.til_create_category_name)
        btnAdd = findViewById(R.id.btn_button)
        btnView = findViewById(R.id.btn_button_view)

        btnAdd.setOnClickListener { addCategoria() }
        btnView.setOnClickListener { getCategoria() }


    }

    private fun getCategoria() {
        val catList = sqliteHelper.getAllCategorias();
        Log.e("ppp", "${catList.size}")
        adapter?.addItems(catList)


    }

    private fun addCategoria() {
        val name = edName.text.toString();
        if (name.isBlank()) {
            Toast.makeText(this, "Coloque datos por favor", Toast.LENGTH_LONG).show();

        } else {
            val cat = CategoriaModel(name = name)
            val status = sqliteHelper.insertCategoria(cat);
            if (status > -1) {
                Toast.makeText(this, "Categoria añadida", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this);
        adapter = CategoriaAdapter();
        recyclerView.adapter = adapter

    }


    private fun clearEditText() {
        edName.setText("")
    }


}