package com.example.meesho

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter(val dataset:List<Product>,private val context:Context,private val onProductClickListener:OnProductClickListener) : RecyclerView.Adapter<ProductAdapter.RecView>() {
    interface OnProductClickListener {
        fun onItemClick(product: Product)
    }

   inner class RecView(private val itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
      val productImg = itemView.findViewById<ImageView>(R.id.productImg)
      val productTitle = itemView.findViewById<TextView>(R.id.title)
      val productPrice = itemView.findViewById<TextView>(R.id.price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val product = dataset[position]
                onProductClickListener.onItemClick(product)
            }
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecView {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false)
        return RecView(view)
    }

    override fun getItemCount(): Int {
       return dataset.size
    }

    override fun onBindViewHolder(holder: RecView, position: Int) {
        val currentItem = dataset[position]
        holder.apply {
            productImg.setImageResource(currentItem.img)
            productTitle.text = currentItem.title
            productPrice.text =  "$" + currentItem.price.toString()

        }

    }

}



