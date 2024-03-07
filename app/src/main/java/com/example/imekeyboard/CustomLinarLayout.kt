package com.example.imekeyboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.example.imekeyboard.adapter.EmojiClicked
import com.example.imekeyboard.adapter.MyRvAdapter
import com.example.imekeyboard.util.LottieUtil
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.NetworkInterface
import java.util.logging.Handler
import kotlin.coroutines.CoroutineContext

@SuppressLint("SuspiciousIndentation")
class CustomLinarLayout(context: Context, attributeSet: AttributeSet): LinearLayout(context)  , EmojiClicked {


    private var rv: RecyclerView
    private var linearLayoutManager: LinearLayoutManager
    private var myRvAdapter: MyRvAdapter
    private val links: ArrayList<String> = ArrayList()
    private val handler = android.os.Handler()
    private var time: Long = 0
    private val userRef: DatabaseReference
    private val userRef0 : DatabaseReference
    private val mainHandler = android.os.Handler(Looper.getMainLooper())
    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    var popupWindow : PopupWindow? =null

    private var database: FirebaseDatabase

    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val pno = sharedPreferences.getLong("phone_no" , 0)


    init {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_linear_layout, this)

//
        rv = findViewById(R.id.sticker_RV)


                links.add("\uD83D\uDE07")
                links.add("\uD83E\uDD70")
                links.add("\uD83C\uDF82")
                links.add("\uD83E\uDD29")
                links.add("\uD83D\uDC95")
                links.add("\uD83C\uDF89")
                links.add("\uD83D\uDE0C")
                links.add("❤\uFE0F")
                links.add("\uD83E\uDD73")
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





        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        myRvAdapter = MyRvAdapter(this)
        rv.layoutManager = linearLayoutManager

        rv.isVisible = true
        myRvAdapter.updateEmojilist(links)
        rv.adapter = myRvAdapter
        database =
            FirebaseDatabase.getInstance("https://keyboard-demo-80aaf-default-rtdb.asia-southeast1.firebasedatabase.app/")
        userRef0= database.reference.child(pno.toString())
        userRef = database.reference.child(pno.toString())

    }


    override fun onEmojiClicked(int: Int) {
//
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // Inflate the custom layout/view
        val popupView: View? = inflater.inflate(R.layout.popup_view, null)
        val lottie: LottieAnimationView = popupView!!.findViewById(R.id.ad)

        var array: ArrayList<String> = ArrayList()
        var array2: ArrayList<String> = ArrayList()
        time = System.currentTimeMillis()
        array.add(time.toString())
        array.add(links[int])

        val d = LottieLog(time ,links[int] )

        Log.d("Thread main" , Thread.currentThread().name)


        userRef0.setValue(array).addOnSuccessListener {

        }
        array.clear()

        coroutineScope.launch {



            withContext(Dispatchers.IO) {
                Log.d("Thread IO" , Thread.currentThread().name)

                database.reference.addChildEventListener(object : ChildEventListener{
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        Log.d("child event" , "$snapshot")
                        var key  = snapshot.key
                        var value = snapshot.value.toString()

//                        for responsive recycler view

                        var arr= snapshot.value as ArrayList<*>


                        var a :  Long = 0
                        var b : String = ""

                        var x =0
                        for ( w in arr) {
                            if (x==0) a = w.toString().toLong()
                            if (x==1) b = w.toString()
                            x++
                        }
                        val ll = arrayListOf(LottieLog(a,b))
                        LottieUtil.all[key!!.toLong()] = ll
//                        Log.d("Checking the data" , LottieUtil.all.toString())
                        LottieUtil.allRV[key.toLong()] = b
                        Log.d("Checking RV data" , LottieUtil.allRV.toString())



                        if (value.contains("\uD83E\uDD73") || value.contains("\uD83C\uDF89") || value.contains("\uD83C\uDF82") )
                            lottie.setAnimation(R.raw.l1)
                        else if (value.contains("\uD83D\uDE18") || value.contains("\uD83D\uDC97") || value.contains("♥\uFE0F")  ||value.contains("\uD83E\uDD70") || value.contains("\uD83D\uDC95") || value.contains("❤\uFE0F"))
                            lottie.setAnimation(R.raw.l2)
                        else if (value.contains("\uD83D\uDE0A") || value.contains("\uD83D\uDE07") || value.contains("\uD83E\uDD29") || value.contains("\uD83D\uDE0C"))
                            lottie.setAnimation(R.raw.l3)
                        else lottie.setAnimation(R.raw.firework)





//                database.reference.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//
//                        array2.add(snapshot.value.toString())
//                        Log.d("1111tetstnlkd" , snapshot.value.toString())
//                        var x = snapshot.value.toString()
//
//                        if (array2[0].contains("\uD83E\uDD73") || array2[0].contains("\uD83C\uDF89") || array2[0].contains("\uD83C\uDF82") )
//                            lottie.setAnimation(R.raw.l1)
//                        else if (array2[0].contains("\uD83D\uDE18") || array2[0].contains("\uD83D\uDC97") || array2[0].contains("♥\uFE0F")  ||array2[0].contains("\uD83E\uDD70") || array2[0].contains("\uD83D\uDC95") || array2[0].contains("❤\uFE0F"))
//                            lottie.setAnimation(R.raw.l2)
//                        else if (array2[0].contains("\uD83D\uDE0A") || array2[0].contains("\uD83D\uDE07") || array2[0].contains("\uD83E\uDD29") || array2[0].contains("\uD83D\uDE0C"))
//                            lottie.setAnimation(R.raw.l3)
//                        else lottie.setAnimation(R.raw.firework)

//                        when (x) {
//                            "\uD83E\uDD73", "\uD83C\uDF89", "\uD83C\uDF82" -> lottie.setAnimation(R.raw.l1)
//                            "❤\uFE0F", "\uD83D\uDC95", "\uD83E\uDD70", "♥\uFE0F", "\uD83D\uDC97", "\uD83D\uDE18" -> lottie.setAnimation(R.raw.l2)
//                            "\uD83D\uDE0C", "\uD83E\uDD29", "\uD83D\uDE07", "\uD83D\uDE0A" -> lottie.setAnimation(R.raw.l3)
//                            else -> lottie.setAnimation(R.raw.firework)
//                        }

                            // Create the popup window
                            mainHandler.post {
                                Log.d("Thread main" , Thread.currentThread().name)

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
//                                popupWindow.dismiss()
                                }

                                // Show the popup window
                                popupWindow?.showAsDropDown(this@CustomLinarLayout, 0, 0)
                                popupWindow?.showAtLocation(
                                    this@CustomLinarLayout,
                                    Gravity.TOP or Gravity.START,
                                    0,
                                    0
                                )
                                popupWindow?.isOutsideTouchable = true


                                handler.postDelayed({
                                    Log.d("Thread 3" , Thread.currentThread().name)
                                    popupWindow?.dismiss()
                                    lottie.cancelAnimation()
                                    lottie.destroyDrawingCache()
                                    popupView.destroyDrawingCache()
//                                    handler.removeCallbacksAndMessages(null)
                                    mainHandler.removeCallbacksAndMessages(null)
                                }, 2000)
                                Log.d("Thread main" , Thread.currentThread().name)
                            }
                        array2.clear()
                        Log.d("Thread IO" , Thread.currentThread().name)
                    }

                        override fun onChildRemoved(snapshot: DataSnapshot) {
                }

                        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                }

                        override fun onCancelled(error: DatabaseError) {
                }

            })
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacksAndMessages(null)
        mainHandler.removeCallbacksAndMessages(null)
//        coroutineScope.coroutineContext.cancel()

    }
}

//{
////
//    val inflater =
//        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    // Inflate the custom layout/view
//    val popupView: View? = inflater.inflate(R.layout.popup_view, null)
//    val lottie: LottieAnimationView = popupView!!.findViewById(R.id.ad)
//
//    var array : ArrayList<String> = ArrayList()
//    var array2 : ArrayList<String> = ArrayList()
//    time=System.nanoTime()
//    array.add(time.toString())
//    array.add(links[int])
//
//
//    userRef.setValue(array)
//    array.clear()
//
//    Thread {
//        userRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                array2.add(snapshot.value.toString())
//                var x = snapshot.value.toString()
//                Log.d("snapshot", array2.toString())
//
//                if(array2[0] != time.toString())
//                    Log.d("Time MAtched" , "here")
//                if (!x.contains(time.toString())) {
//                    Log.d("Time didn't matched", time.toString())
//                    // Create the popup window
//                    val popupWindow = PopupWindow(
//                        popupView,
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        false
//                    )
//
//                    lottie.visibility = View.VISIBLE
//                    lottie.cancelAnimation()
//                    lottie.playAnimation()
//
//                    // Set an elevation for the popup window
//                    popupWindow.elevation = 10.0F
//
//                    // Set a dismiss listener for the popup window
//                    popupWindow.setOnDismissListener {
//                        // Handle popup dismiss
////                                popupWindow.dismiss()
//                    }
//
//                    // Show the popup window
//                    popupWindow.showAsDropDown(this@CustomLinarLayout, 0, 0)
//                    popupWindow.showAtLocation(
//                        this@CustomLinarLayout,
//                        Gravity.TOP or Gravity.START,
//                        0,
//                        0
//                    )
//                    popupWindow.isOutsideTouchable = true
//
//
//                    handler.postDelayed({
//                        popupWindow.dismiss()
//                        lottie.cancelAnimation()
//                        lottie.destroyDrawingCache()
//                        popupView.destroyDrawingCache()
//                    }, 3000)
//                } else
//                    Log.d("Timematched", time.toString())
//                array2.clear()
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//    }.start()
//}