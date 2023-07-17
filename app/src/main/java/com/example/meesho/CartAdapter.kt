package com.example.meesho

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val list: List<Product>,private val context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
  private var onItemClickListener:OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(product: Product)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.productImg)
        val price: TextView = itemView.findViewById(R.id.price)
        val title: TextView = itemView.findViewById(R.id.title)
        val size:TextView = itemView.findViewById(R.id.size)
        val color:TextView = itemView.findViewById(R.id.color)
        val delete: AppCompatImageButton = itemView.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.apply {
            this.img.setImageResource(currentItem.img)
            this.price.text = currentItem.title
            this.title.text = currentItem.price.toString()
            this.size.text = currentItem.size
            this.color.text = currentItem.color
            this.delete.setOnClickListener {
                onItemClickListener?.onClick(currentItem)
            }

        }
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}