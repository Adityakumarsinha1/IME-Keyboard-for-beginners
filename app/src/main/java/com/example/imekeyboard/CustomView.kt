package com.example.imekeyboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomView (context: Context, attrs: AttributeSet) : View(context, attrs) {


    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320

    private val mouthPath = Path()



    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        // 1
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // 2
        val radius = size / 2f

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)

    }

    private fun drawEyes(canvas: Canvas) {
        // 1
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

// 2
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

// 3
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas) {
        // 1
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
// 2
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
// 3
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
// 4
        paint.color = mouthColor
        paint.style = Paint.Style.FILL
// 5
        canvas.drawPath(mouthPath, paint)

    }
}