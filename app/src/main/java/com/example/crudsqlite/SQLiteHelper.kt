package com.example.crudsqlite

import android.content.*;
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log
import androidx.core.content.contentValuesOf
import java.lang.Exception


class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2;
        private const val DATABASE_NAME = "ventas.db";
        private const val TB_Categoria = "categoria";
        private const val TB_Producto = "producto";
        private const val IDC = "idcategoria";
        private const val IDP = "idproducto";
        private const val NOMPROD = "nomprod";
        private const val PRICE = "price";
        private const val STOCK = "stock";
        private const val NAME = "name";
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTbCategoria =
            ("CREATE TABLE  $TB_Categoria( $IDC INTEGER PRIMARY KEY,$NAME TEXT )")
        val createTbProducto =
            ("CREATE TABLE  $TB_Producto( $IDP INTEGER PRIMARY KEY,$NOMPROD TEXT, $PRICE REAL, $STOCK INTEGER , $IDC INTEGER, FOREIGN KEY($IDC) REFERENCES $TB_Categoria($IDC) )")
        db?.execSQL(createTbCategoria)

        db?.execSQL(createTbProducto)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TB_Categoria")
        db!!.execSQL("DROP TABLE IF EXISTS $TB_Producto")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TB_Categoria")
        db!!.execSQL("DROP TABLE IF EXISTS $TB_Producto")
        onCreate(db)
    }

    fun insertCategoria(cat: CategoriaModel): Long {
        val db = this.writableDatabase;
        val contentValues = ContentValues();
        contentValues.put(IDC, cat.id);
        contentValues.put(NAME, cat.name);
        val success = db.insert(TB_Categoria, null, contentValues)
        db.close()
        return success


    }

    fun getAllCategorias(): ArrayList<CategoriaModel> {
        val catList: ArrayList<CategoriaModel> = ArrayList();
        val selectQuery = "SELECT * FROM $TB_Categoria";
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: Exception) {

            e.printStackTrace();
            db.execSQL(selectQuery);
            return ArrayList();
        }
        var id: Int
        var name: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(IDC))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                val cat = CategoriaModel(id = id, name = name)
                catList.add(cat);
            } while (cursor.moveToNext())
        }
        return catList;
    }
    fun updateCategoria(cat : CategoriaModel) : Int{
        val db = this.writableDatabase;
        val contentValues = ContentValues()
        contentValues.put(IDC,cat.id);
        contentValues.put(NAME, cat.name)
        val success = db.update(TB_Categoria,contentValues, "id = " + cat.id, null);
        db.close();
        return success;
    }
    fun deleteCategoria(id:Int): Int{
        val db = this.writableDatabase;
        val contentValue = ContentValues();
        contentValue.put(IDC,id)
        val success = db.delete(TB_Categoria, "id = " + id, null)
        db.close()
        return success
    }




    fun insertProducto(product: ProductModel): Long {
        val db = this.writableDatabase;
        val contentValues = ContentValues()
        contentValues.put(NOMPROD, product.nomprod)
        contentValues.put(PRICE, product.precio)
        contentValues.put(STOCK, product.stock)
        contentValues.put(IDC, product.idcategoria)

        val success = db.insert(TB_Producto, null, contentValues)
        db.close()
        return success
    }

    fun deleteProducto(idproducto:Int):Int{
        val db = this.writableDatabase

        val success = db.delete(TB_Producto,"$IDP = $idproducto",null)

        return success
    }

    fun getAllProductos(): ArrayList<ProductModel> {
        val db = this.readableDatabase
        val productList: ArrayList<ProductModel> = ArrayList();
        val cursor: Cursor?
        val selectQuery = "SELECT * FROM $TB_Producto INNER JOIN $TB_Categoria on $TB_Producto.$IDC = $TB_Categoria.$IDC";

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery);
            return ArrayList();
        }
        if (cursor.moveToFirst()) {
            do {
                productList.add(
                    ProductModel(
                        cursor.getInt(cursor.getColumnIndex(IDP)),
                        cursor.getString(
                            cursor.getColumnIndex(
                                NOMPROD
                            )
                        ),
                        cursor.getDouble(cursor.getColumnIndex(PRICE)),
                        cursor.getInt(cursor.getColumnIndex(STOCK)),
                        cursor.getInt(cursor.getColumnIndex(IDC)),
                        cursor.getString(cursor.getColumnIndex(NAME))
                    )
                )
            } while (cursor.moveToNext())
        }
        db.close()
        return productList
    }


}