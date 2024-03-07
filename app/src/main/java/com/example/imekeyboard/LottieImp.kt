//package com.example.imekeyboard
//
//import android.R
//import android.annotation.SuppressLint
//import android.content.Context
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.LinearLayout
//import android.widget.PopupWindow
//import android.widget.TextView
//import android.widget.Toast
//import com.airbnb.lottie.LottieAnimationView
//
//
//class LottieImp {
//    var lottie: LottieAnimationView? = null
//
//    @SuppressLint("ClickableViewAccessibility")
//    fun showPopupWindow(context: Context) {
//
//
//
//
//
//        //Create a View object yourself through inflater
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//        val popupView= inflater.inflate(R.layout., null)
//
//        //Specify the length and width through constants
//        val width = LinearLayout.LayoutParams.MATCH_PARENT
//        val height = LinearLayout.LayoutParams.MATCH_PARENT
//
//        //Make Inactive Items Outside Of PopupWindow
//        val focusable = true
//
//        //Create a window with our parameters
//        val popupWindow = PopupWindow(popupView, width, height, focusable)
//
//        //Set the location of the window on the screen
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
//
//        //Initialize the elements of our window, install the handler
//        val test2 = popupView.findViewById<TextView>(R.id.titleText)
//        test2.setText(R.string.textTitle)
//        val buttonEdit = popupView.findViewById<Button>(R.id.messageButton)
//        buttonEdit.setOnClickListener { //As an example, display the message
//            Toast.makeText(view.context, "Wow, popup action button", Toast.LENGTH_SHORT).show()
//        }
//
//
//        //Handler for clicking on the inactive zone of the window
//        popupView.setOnTouchListener { v, event -> //Close the window when clicked
//            popupWindow.dismiss()
//            true
//        }
//    }
//}