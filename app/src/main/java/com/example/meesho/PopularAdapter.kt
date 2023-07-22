package com.example.meesho

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PopularAdapter(val list: List<Product>,val context:Context) : RecyclerView.Adapter<PopularAdapter.PopViewModel>(){

    inner class PopViewModel(itemView: View):RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.productImg)
        val title = itemView.findViewById<TextView>(R.id.title)
        val price = itemView.findViewById<TextView>(R.id.price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewModel {
        val view = LayoutInflater.from(context).inflate(R.layout.popular_item,parent,false)
        return PopViewModel(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: PopViewModel, position: Int) {
        val currentItem = list[position]
        holder.apply {
            this.img.setImageResource(currentItem.img)
            this.title.text = currentItem.title
            this.price.text = currentItem.price.toString()
        }
    }
}