package com.example.imekeyboard.adapter

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.imekeyboard.databinding.RvStickersBinding


class MyRvAdapter(
    private val listner : EmojiClicked
) : RecyclerView.Adapter<MyRvAdapter.MenuViewHolder>() {


    var links= ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        Log.d("Deepak", "onCreateViewHolder: ")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvStickersBinding.inflate(layoutInflater, parent, false)


        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.myTextView!!.text = links[position]

        holder.myTextView!!.setOnClickListener {
            listner.onEmojiClicked(position)
        }
    }

    override fun getItemCount(): Int {
        Log.d("aditya_1", "getItemCount: ")
        return links.size
    }

    fun updateEmojilist(list: ArrayList<String>){
        this.links.clear()
        this.links.addAll(list)
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(private val binding: RvStickersBinding) : RecyclerView.ViewHolder(binding.root) {
        var myTextView: TextView? = binding.cvemoji
//        var lotty : LottieAnimationView? = binding.lavThumbUp

    }
}

interface EmojiClicked{
    fun onEmojiClicked(int: Int)
}