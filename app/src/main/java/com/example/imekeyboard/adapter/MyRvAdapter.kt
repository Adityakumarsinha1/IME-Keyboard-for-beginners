package com.example.imekeyboard.adapter

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imekeyboard.databinding.RvStickersBinding
import java.net.URL


class MyRvAdapter(
    private val context: Context,
    private val links: ArrayList<String>
) : RecyclerView.Adapter<MyRvAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvStickersBinding.inflate(layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return links.size
    }

    inner class MenuViewHolder(private val binding: RvStickersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Glide.with(context)
                .load(links[position])
                .centerCrop()
                .placeholder(R.drawable.arrow_down_float)
                .into(binding.cvsticker)
        }
    }
}