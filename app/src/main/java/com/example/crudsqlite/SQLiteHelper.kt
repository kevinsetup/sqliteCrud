package com.example.crudsqlite
import android.content.*;
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log
import androidx.core.content.contentValuesOf
import java.lang.Exception


class SQLiteHelper(context: Context)
    :   SQLiteOpenHelper(context, DATABASE_NAME,  null,  DATABASE_VERSION){

    companion object {
            private const val DATABASE_VERSION = 1;
            private const val DATABASE_NAME = "ventas.db";
            private const val TB_Categoria = "categoria";
            private const val ID = "id";
            private const val NAME = "name";



    }



    override fun onCreate(db: SQLiteDatabase?) {
        val createTbCategoria= ("CREATE TABLE " + TB_Categoria + "( " + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT" + ")")
        db?.execSQL(createTbCategoria);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS $TB_Categoria")
        onCreate(db);
    }
     fun insertCategoria(cat : CategoriaModel) : Long {
        val db = this.writableDatabase;
        val contentValues = ContentValues();
        contentValues.put(ID, cat.id);
        contentValues.put(NAME , cat.name);
        val success = db.insert(TB_Categoria, null, contentValues)
        db.close()
        return success;


    }
    fun getAllCategorias() : ArrayList<CategoriaModel>{
        val catList : ArrayList<CategoriaModel> = ArrayList();
        val selectQuery = "SELECT * FROM $TB_Categoria";
        val db = this.readableDatabase
        val cursor : Cursor ?
        try {
            cursor = db.rawQuery(selectQuery , null)

        }catch (e : Exception){

            e.printStackTrace();
            db.execSQL(selectQuery);
            return ArrayList();
        }
        var id : Int
        var name : String
        if(cursor.moveToFirst())
        {
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                val cat = CategoriaModel(id = id, name = name)
                catList .add(cat);
            }while (cursor.moveToNext())
        }
        return catList;
    }


}