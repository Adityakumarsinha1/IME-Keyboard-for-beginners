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

        links.add("\uD83D\uDC8B")
        links.add("\uD83D\uDE18")
        links.add("\uD83D\uDE05")
        links.add("\uD83D\uDC4C")
        links.add("\uD83D\uDE4F")
        links.add("\uD83E\uDD23")
        links.add("\uD83D\uDE1D")
        links.add("\uD83D\uDE02")
        links.add("\uD83D\uDE2D")
        links.add("\uD83D\uDC4D")
        links.add("\uD83E\uDD70")
        links.add("\uD83D\uDC4D")
        links.add("\uD83C\uDF39")
        links.add("\uD83D\uDE21")
        links.add("\uD83D\uDE0E")
        links.add("\uD83D\uDE0B")
        links.add("\uD83D\uDE0D")
        links.add("\uD83E\uDD18")
        links.add("\uD83D\uDE34")
        links.add("\uD83C\uDF82")
        links.add("\uD83D\uDE18")
        links.add("\uD83E\uDD70")
        links.add("\uD83E\uDD11")
        links.add("\uD83E\uDD14")
        links.add("\uD83D\uDE33")
        links.add("\uD83E\uDD7A")
        links.add("\uD83D\uDE01")
        links.add("\uD83D\uDD95")
        links.add("\uD83D\uDC4B")
        links.add("\uD83D\uDC9E")
        links.add("\uD83D\uDC8F")
        links.add("\uD83D\uDC94\uD83D\uDC94")
        links.add("\uD83D\uDC90")
        links.add("\uD83D\uDC98")
        links.add("\uD83D\uDE1A")
        links.add("\uD83E\uDD23")
        links.add("\uD83D\uDE05")
        links.add("\uD83E\uDD17")
        links.add("\uD83D\uDE14")
        links.add("\uD83D\uDE0D")
        links.add("\uD83E\uDD70")
        links.add("\uD83D\uDE0D")
        links.add("\uD83D\uDE17")
        links.add("\uD83E\uDD2D")
        links.add("\uD83E\uDD73")
        links.add("\uD83E\uDD73")
        links.add("\uD83D\uDE02")
        links.add("\uD83E\uDD19")
        links.add("\uD83D\uDC4F")
        links.add("\uD83D\uDD25")
        links.add("\uD83E\uDD2C")












//
//
//
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        myRvAdapter =  MyRvAdapter(context, links)
        rv.layoutManager = linearLayoutManager
        rv.adapter = myRvAdapter
    }
}

/**
 * 💋
 * 😘
 * ❤️
 * 👌
 * 🙏
 * 🤣
 * 😝
 * 😂
 * 😭
 * 👍
 * 🥰
 * 🌹
 * 😡
 * 😎
 * 😋
 * 😍
 * 🤘
 * 😴
 * 🎂
 * 🥰😘
 * 🤑
 * 🤔
 * 😳
 * 🥺
 * 😁
 * 🖕
 * 👋
 * 💞
 * 🛕
 * ❤️
 * 💔
 * 💐
 * 💘
 * 😚
 * 😅🤣
 * 🤗
 * 😔
 * 😍😍🥰
 * 😗
 * 😂🤭
 * 🥳
 * 💏
 * 🤙
 * 🤬
 * 😊❤️
 * 🔥🔥❤️
 * 😅
 * 👏
 */