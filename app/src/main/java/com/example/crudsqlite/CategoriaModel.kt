package com.example.crudsqlite
import java.util.*;

data class CategoriaModel(
      var id : Int = getAutoId(),
      var name : String = ""


){
    companion object{
        fun getAutoId() : Int {
            val random = Random()
            return random.nextInt(100);
        }

    }

    override fun toString(): String {
        return name
    }
};