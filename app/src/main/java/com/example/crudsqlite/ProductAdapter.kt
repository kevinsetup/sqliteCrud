package com.example.crudsqlite

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductAdapter(var scheduleList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_product_item, parent, false)

        var deleteButton: Button = view.findViewById(R.id.btn_product_item_delete)
        deleteButton.setOnClickListener {
            Log.w(ContentValues.TAG, "INgeniero quevin")
            MaterialAlertDialogBuilder(parent.context)
                .setMessage(parent.resources.getString(R.string.delete_dialog))
                .setNegativeButton(parent.resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(parent.resources.getString(R.string.accept)) { dialog, which ->
                    // Respond to positive button press
                }
                .show()
        }
        return ProductViewHolder(view)

    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.assignData(scheduleList[position])
    }

    override fun getItemCount(): Int = scheduleList.size

    class ProductViewHolder : RecyclerView.ViewHolder {
        var name: TextView
        var price: TextView
        var stock: TextView
        var nomcat: TextView

        constructor(view: View) : super(view) {
            this.name = view.findViewById(R.id.tv_product_item_name)
            this.price = view.findViewById(R.id.tv_product_item_price)
            this.stock = view.findViewById(R.id.tv_product_item_stock)
            this.nomcat = view.findViewById(R.id.tv_product_item_category_name)
        }

        fun assignData(productModel: ProductModel) {
            this.name.text = productModel.nomprod
            this.price.text = productModel.precio.toString()
            this.stock.text = productModel.stock.toString()
            this.nomcat.text = productModel.nomcat

        }

    }
}