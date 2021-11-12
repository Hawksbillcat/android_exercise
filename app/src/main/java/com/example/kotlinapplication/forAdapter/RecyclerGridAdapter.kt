package com.example.kotlinapplication.forAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R

class RecyclerGridAdapter(private val mData: ArrayList<Int>, private val mContext: Context): RecyclerView.Adapter<RecyclerGridAdapter.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val img:ImageView = itemView.findViewById(R.id.smile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerGridAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gird_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(mData[position])
        holder.img.setOnClickListener{
           // Toast.makeText(mContext,"clicked $position",Toast.LENGTH_SHORT).show()
            mData.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
       return mData.size
    }
}