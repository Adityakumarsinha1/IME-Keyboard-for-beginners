package com.example.imekeyboard

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlin.properties.Delegates

class KeyboardDragDelegate(private val context: Context, private val window: Window) {

    companion object{
        var initialY: Int?= null
        var initialX: Int?=null
        var initialTouchX: Float?=null
        var initialTouchY: Float?=null
        var initCenterY = 0

        var newCenterX :Int?= null
        var newCenterY :Int? = null
    }

    val params =  window.attributes


    private val centerYBound by lazy {
//        val screenHeight = context.displayMetrics.heightPixels
//        val windowHeight = window.attributes.height
//        return@lazy IntRange(0 + windowHeight + getStatusBarHeight(), screenHeight - windowHeight - getNavBarHeight())

        val halfScreenHeight = context.displayMetrics.heightPixels / 2
        return@lazy IntRange(-halfScreenHeight, halfScreenHeight)
    }

    private val centerXBound by lazy {
        val halfScreenWidth = context.displayMetrics.widthPixels /2
        return@lazy IntRange(-halfScreenWidth, halfScreenWidth)
    }

    fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("MotionEvent", "ACTION_DOWN")
                initialX = window.attributes.x
                initialY = window.attributes.y
                initialTouchX = event.rawX
                initialTouchY = event.rawY

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val touchXMovement = event.rawX - initialTouchX!!
                val touchYMovement = initialTouchY?.minus(
                    event.rawY
                )

                newCenterX = initialX?.plus(touchXMovement.toInt())

                if (touchYMovement != null) {
                    newCenterY = initCenterY + touchYMovement.toInt()
                }

                if (isWithinXBound(newCenterX!!) && isWithinYBound(newCenterY!!)) {
//                    val params = window.attributes
                    params.x = newCenterX as Int
                    params.y = newCenterY as Int
                    updateViewLayout(params)
                    Log.d("MotionEvent", "A ACTION_MOVE")

                    initialX = event.rawX.toInt()
                    initialY = event.rawY.toInt()
                    return true
                }
                Log.d("MotionEvent", "B ACTION_MOVE ${isWithinXBound(newCenterX!!)} ${isWithinYBound(
                    newCenterY!!
                )}")
                return false
            }
        }
        return false
    }

    private fun updateViewLayout(params: WindowManager.LayoutParams) {
        window.attributes = params
    }

    private fun isWithinXBound(newX: Int): Boolean {
        return newX in centerXBound
    }

    private fun isWithinYBound(newY: Int): Boolean {
        return newY in centerYBound
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    private fun getNavBarHeight(): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)/2
        }
        return result
    }
}

val Context.displayMetrics: DisplayMetrics
    get() {
        val metrics = DisplayMetrics()
        val display = windowManager.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }
val Context.windowManager: WindowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager