package com.example.crudsqlite

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductAdapter(var scheduleList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_product_item, parent, false)


        deleteButton.setOnClickListener {
            Log.w(ContentValues.TAG, "INgeniero quevin")
            MaterialAlertDialogBuilder(parent.context)
                .setMessage(parent.resources.getString(R.string.delete_dialog))
                .setNegativeButton(parent.resources.getString(R.string.decline)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(parent.resources.getString(R.string.accept)) { dialog, which ->
                    SQLiteHelper(parent.context).deleteProducto(1)
                    Toast.makeText(
                        parent.context,
                        "Successfully deleted element",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }

        editButton.setOnClickListener {

        }

        return ProductViewHolder(view)

    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.assignData(scheduleList[position])
    }

    override fun getItemCount(): Int = scheduleList.size

    class ProductViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
        var name: TextView
        var price: TextView
        var stock: TextView
        var nomcat: TextView
        var deleteButton: Button
        var editButton: Button


        constructor(view: View) : super(view) {
            this.name = view.findViewById(R.id.tv_product_item_name)
            this.price = view.findViewById(R.id.tv_product_item_price)
            this.stock = view.findViewById(R.id.tv_product_item_stock)
            this.nomcat = view.findViewById(R.id.tv_product_item_category_name)
            this.deleteButton = view.findViewById(R.id.btn_product_item_delete)
            this.editButton = view.findViewById(R.id.btn_product_item_edit)
        }

        fun assignData(productModel: ProductModel) {
            this.name.text = productModel.nomprod
            this.price.text = productModel.precio.toString()
            this.stock.text = productModel.stock.toString()
            this.nomcat.text = productModel.nomcat

        }

        override fun onClick(v: View?) {
            val position = adapterPosition

        }

    }
}