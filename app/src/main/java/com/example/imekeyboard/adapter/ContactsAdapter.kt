package com.example.imekeyboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.imekeyboard.R
import com.example.imekeyboard.databinding.RvContactsBinding
import com.example.imekeyboard.databinding.RvStickersBinding
import com.example.imekeyboard.util.LottieUtil

class ContactsAdapter(
    private val listner : ContactClicked
) : RecyclerView.Adapter<ContactsAdapter.MenuViewHolder>() {


    var links= ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        Log.d("aditya1", "onCreateViewHolder: ")



        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_contacts, parent, false)
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = RvContactsBinding.inflate(layoutInflater, parent, false)

        return MenuViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {


        holder.myTextView!!.text =links[position]

        holder.imageView!!.setOnClickListener {
            Log.d("aditya1" , "passed")
            listner.onContactClicked(links[position].toLong() , position)
        }
    }

    override fun getItemCount(): Int {
//        test.size

        Log.d("aditya1", links.size.toString())
        return links.size
    }

    fun updateEmojilist(list: ArrayList<String>){
        this.links.clear()
        this.links.addAll(list)
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        var myTextView: TextView? = binding.findViewById(R.id.contact_no_TV)
        var imageView : ImageView? = binding.findViewById(R.id.image)
    }
}

interface ContactClicked{
    fun onContactClicked(key: Long , int: Int)
}