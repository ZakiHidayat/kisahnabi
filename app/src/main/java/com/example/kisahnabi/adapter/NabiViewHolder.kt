package com.example.kisahnabi.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kisahnabi.databinding.ItemNabiBinding
import com.example.kisahnabi.model.ResponseNabiItem

class NabiViewHolder(val nabiBinding: ItemNabiBinding) :
    RecyclerView.ViewHolder(nabiBinding.root) {
        fun bindview(item : ResponseNabiItem) {
            itemView.run {
                nabiBinding.txtNamanabi.text = item.nama
                nabiBinding.imgNabi.load(item.avatar)
            }
        }
}
