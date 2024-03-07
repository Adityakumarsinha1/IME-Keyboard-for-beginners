package com.example.imekeyboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.imekeyboard.adapter.ContactClicked
import com.example.imekeyboard.adapter.ContactsAdapter
import com.example.imekeyboard.adapter.EmojiClicked
import com.example.imekeyboard.adapter.MyRvAdapter
import com.example.imekeyboard.databinding.PopupViewBinding
import com.example.imekeyboard.util.LottieUtil
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.NetworkInterface
import java.util.logging.Handler
import kotlin.coroutines.CoroutineContext

@SuppressLint("SuspiciousIndentation")
class Contcts(context: Context, attributeSet: AttributeSet): LinearLayout(context)  , ContactClicked{

//
    private var rv: RecyclerView
    private var linearLayoutManager: LinearLayoutManager
    private var myRvAdapter: ContactsAdapter
    private val links: ArrayList<String> = ArrayList()
    private val emoji: ArrayList<String> = ArrayList()

    private var database: FirebaseDatabase

    private var popupWindow : PopupWindow? =null
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val pno = sharedPreferences.getLong("phone_no" , 0)

    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.contacts, this)


        database =
            FirebaseDatabase.getInstance("https://keyboard-demo-80aaf-default-rtdb.asia-southeast1.firebasedatabase.app/")


        Log.d("value" , LottieUtil.all.keys.toString())

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv = findViewById(R.id.contacts_RV)
        myRvAdapter = ContactsAdapter(this)
        rv.layoutManager =linearLayoutManager
        rv.adapter = myRvAdapter
        myRvAdapter.updateEmojilist(links)
        myRvAdapter.notifyDataSetChanged()


        database.reference.get().addOnSuccessListener {
            for (snapshot in it.children) {

                Log.d("jvndnwldks", snapshot.key.toString())

                var x = snapshot.value as ArrayList<*>
                var emoji: String = ""
                var time: Long = 0
                for ((count, t) in x.withIndex()) {
                    Log.d("for", "$t")
                    if (count == 0)
                        time = t.toString().toLong()
                    else {
                        emoji = t.toString()
                        this.emoji.add(emoji)
                    }

                }

                var a = LottieLog(time, emoji)
                LottieUtil.all[snapshot.key!!.toLong()] = arrayListOf(a)
                LottieUtil.allRV[snapshot.key!!.toLong()] = emoji

                Log.d("Adapter data" , LottieUtil.allRV.toString())
            }

            postDelayed({
                for ( x in LottieUtil.all){
                    Log.d("value" , this.emoji.toString())
                    links.add(x.key.toString())
                }

                linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                rv = findViewById(R.id.contacts_RV)
                myRvAdapter = ContactsAdapter(this)
                rv.layoutManager = linearLayoutManager
                rv.adapter = myRvAdapter
                myRvAdapter.updateEmojilist(links)
                myRvAdapter.notifyDataSetChanged()
            },500)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
//        handler.removeCallbacksAndMessages(null)
//        mainHandler.removeCallbacksAndMessages(null)
//        coroutineScope.coroutineContext.cancel()

    }

    override fun onContactClicked(key  : Long , int: Int) {


        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // Inflate the custom layout/view
        val popupView: View? = inflater.inflate(R.layout.popup_view, null)
        val lottie: LottieAnimationView = popupView!!.findViewById(R.id.ad)

        val x= links[int]


//        Log.d("contact clicked" , int.toString())
        val uri = Uri.parse("https://wa.me/$x")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

        var c:String = LottieUtil.allRV[key]!!
        Log.d("hhhhhhhhhhhhh" ,  c.toString())


        Log.d("links" , links.toString())
        if (c.contains("\uD83E\uDD73") || c.contains("\uD83C\uDF89") || c.contains("\uD83C\uDF82") )
            lottie.setAnimation(R.raw.l1)
        else if (c.contains("\uD83D\uDE18") || c.contains("\uD83D\uDC97") || c.contains("♥\uFE0F")  ||c.contains("\uD83E\uDD70") || c.contains("\uD83D\uDC95") || c.contains("❤\uFE0F"))
            lottie.setAnimation(R.raw.l2)
        else if (c.contains("\uD83D\uDE0A") || c.contains("\uD83D\uDE07") || c.contains("\uD83E\uDD29") || c.contains("\uD83D\uDE0C"))
            lottie.setAnimation(R.raw.l3)
        else lottie.setAnimation(R.raw.firework)

        if (LottieUtil.windowShown) {
            Log.d("window shown", LottieUtil.windowShown.toString())


            coroutineScope.launch {
                delay(2000)

                popupWindow?.dismiss()

                popupWindow = PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    false
                )

                lottie.visibility = View.VISIBLE
                lottie.cancelAnimation()
                lottie.playAnimation()

                // Set an elevation for the popup window
                popupWindow?.elevation = 10.0F

                // Set a dismiss listener for the popup window
                popupWindow?.setOnDismissListener {
                    // Handle popup dismiss
                    popupWindow?.dismiss()
                }

                // Show the popup window
                popupWindow?.showAsDropDown(this@Contcts, 0, 0)
                popupWindow?.showAtLocation(
                    this@Contcts,
                    Gravity.TOP or Gravity.START,
                    0,
                    0
                )
                popupWindow?.isOutsideTouchable = true
            }
        }
        postDelayed({popupWindow?.dismiss()}, 4000)
    }
}
