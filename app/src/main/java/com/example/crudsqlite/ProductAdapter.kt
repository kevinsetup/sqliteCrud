package com.example.crudsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(var scheduleList: MutableList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_product_item, parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.assignData(scheduleList[position])
    }

    override fun getItemCount(): Int = scheduleList.size

    class ProductViewHolder : RecyclerView.ViewHolder {
        var name: TextView
        var price: TextView
        var stock: TextView

        constructor(view: View) : super(view) {
            this.name = view.findViewById(R.id.tv_product_item_name)
            this.price = view.findViewById(R.id.tv_product_item_price)
            this.stock = view.findViewById(R.id.tv_product_item_stock)
        }

        fun assignData(productModel: ProductModel) {
            this.name.text=productModel.nomprod
            this.price.text=productModel.precio.toString()
            this.stock.text=productModel.stock.toString()
        }
    }
}