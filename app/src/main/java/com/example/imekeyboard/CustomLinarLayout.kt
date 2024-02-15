package com.example.imekeyboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imekeyboard.adapter.MyRvAdapter

class CustomLinarLayout(context: Context , attributeSet: AttributeSet): LinearLayout(context) {


    private  var rv : RecyclerView
    private  var linearLayoutManager: LinearLayoutManager
    private  var myRvAdapter: MyRvAdapter
    private  val links: ArrayList<String>  = ArrayList()

    init {

        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_linear_layout, this)

//
        rv = findViewById(R.id.sticker_RV)
//

        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")
        links.add("https://www.kasandbox.org/programming-images/avatars/spunky-sam.png")

//
//
//
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        myRvAdapter =  MyRvAdapter(context, links)
        rv.layoutManager = linearLayoutManager
        rv.adapter = myRvAdapter
    }
}