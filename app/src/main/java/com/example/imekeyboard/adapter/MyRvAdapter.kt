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
    private val context: Context,
    private val links: ArrayList<String>
) : RecyclerView.Adapter<MyRvAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvStickersBinding.inflate(layoutInflater, parent, false)


        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.myTextView!!.text = links[position]
        holder.myTextView!!.setOnClickListener {

            holder.myTextView!!.visibility =  View.INVISIBLE
            holder.lotty!!.visibility = View.VISIBLE
            holder.lotty!!.setProgress(0f)
            holder.lotty!!.pauseAnimation()
            holder.lotty!!.playAnimation()
            Log.d("RV", links[position])

        }
        holder.lotty!!.setOnClickListener {
            holder.myTextView!!.visibility = View.VISIBLE
            holder.lotty!!.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return links.size
    }

    inner class MenuViewHolder(private val binding: RvStickersBinding) : RecyclerView.ViewHolder(binding.root) {
        var myTextView: TextView? = binding.cvemoji
        var lotty : LottieAnimationView? = binding.lavThumbUp

    }
}