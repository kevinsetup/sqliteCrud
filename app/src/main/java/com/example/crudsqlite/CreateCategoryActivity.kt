package com.example.crudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate : Button
    private lateinit var edName: EditText
    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: CategoriaAdapter? = null;
    private var cat:CategoriaModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        initRecyclerView();

        sqliteHelper = SQLiteHelper(this);
        btnUpdate= findViewById(R.id.btnUpdate)!!
        edName = findViewById(R.id.til_create_category_name)
        btnAdd = findViewById(R.id.btn_button)
        btnView = findViewById(R.id.btn_button_view)


        btnAdd.setOnClickListener { addCategoria() }
        btnView.setOnClickListener { getCategoria() }
        btnUpdate.setOnClickListener {updateCategoria()}
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name , Toast.LENGTH_SHORT).show()
            edName.setText(it.name);
            cat = it;

        }
        adapter?.setOnclickDeleteItem {
            deleteCategoria(it.id)
        }


    }

    private fun getCategoria() {
        val catList = sqliteHelper.getAllCategorias();
        Log.e("ppp", "${catList.size}")
        adapter?.addItems(catList)


    }
    private fun updateCategoria(){
        val name = edName.text.toString();
        if(name === cat?.name){
            Toast.makeText(this, "Registro no actualizado", Toast.LENGTH_LONG).show();
            return
        }
        if(cat == null) return
            val cat = CategoriaModel(id = cat!!.id, name = name)
            val status = sqliteHelper.updateCategoria(cat)
        if(status > -1){
            clearEditText();
            getCategoria()

        }else{
            Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
        }


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
    private fun deleteCategoria(id :Int ){
        if(id == null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Estás seguro de elimianr este elemento?")
        builder.setCancelable(true);
        builder.setPositiveButton("si" ){ dialog, _->
            sqliteHelper.deleteCategoria(id);
            getCategoria()
            dialog.dismiss()
        }
        builder.setNegativeButton("no"){ dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
        }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter

    }


    private fun clearEditText() {
        edName.setText("")
    }


}