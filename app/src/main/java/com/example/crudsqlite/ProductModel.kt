package com.example.crudsqlite

class ProductModel {
    var idproducto: Int
    var nomprod: String
    var precio: Double
    var stock: Int
    var idcategoria: Int
    lateinit var nomcat: String

    constructor(
        idproducto: Int,
        nomprod: String,
        precio: Double,
        stock: Int,
        idcategoria: Int
    ) {
        this.idproducto = idproducto
        this.nomprod = nomprod
        this.precio = precio
        this.stock = stock
        this.idcategoria = idcategoria

    }

    constructor(
        idproducto: Int,
        nomprod: String,
        precio: Double,
        stock: Int,
        idcategoria: Int,
        nomcat: String
    ) {
        this.idproducto = idproducto
        this.nomprod = nomprod
        this.precio = precio
        this.stock = stock
        this.idcategoria = idcategoria
        this.nomcat = nomcat
    }


}