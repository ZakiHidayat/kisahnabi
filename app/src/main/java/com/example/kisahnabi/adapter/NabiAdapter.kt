package com.example.kisahnabi.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kisahnabi.DetailNabi
import com.example.kisahnabi.databinding.ItemNabiBinding
import com.example.kisahnabi.model.ResponseNabiItem

class NabiAdapter : RecyclerView.Adapter<NabiViewHolder>(){
    private val listitemnabi = arrayListOf<ResponseNabiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(items : List<ResponseNabiItem>){
        listitemnabi.clear()
        listitemnabi.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NabiViewHolder {
        val listitemnabibinding = ItemNabiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NabiViewHolder(listitemnabibinding)
    }

    override fun onBindViewHolder(holder: NabiViewHolder, position: Int) {
        val data  = listitemnabi[position]
        holder.bindview(data)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailNabi::class.java)
            intent.putExtra(DetailNabi.EXTRA_DATA, data)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listitemnabi.size
    }
}