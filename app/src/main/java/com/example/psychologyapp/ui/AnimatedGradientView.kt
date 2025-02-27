package com.example.psychologyapp.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class AnimatedGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint()
    private val argbEvaluator = ArgbEvaluator()

    // Пастельные тона для градиента
    private val gradientColors = intArrayOf(
        0xFFE1BEE7.toInt(), // Пастельный фиолетовый
        0xFFB3E5FC.toInt()  // Пастельный голубой
    )
    private val nextGradientColors = intArrayOf(
        0xFFB2DFDB.toInt(), // Пастельный мятный
        0xFFFFF9C4.toInt()  // Пастельный желтый
    )

    private var currentColors: IntArray = gradientColors.copyOf()

    init {
        startGradientAnimation()
    }

    private fun startGradientAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 4000 // 4 секунды для плавного перехода
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                currentColors = intArrayOf(
                    argbEvaluator.evaluate(fraction, gradientColors[0], nextGradientColors[0]) as Int,
                    argbEvaluator.evaluate(fraction, gradientColors[1], nextGradientColors[1]) as Int
                )
                invalidate() // Перерисовываем View
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val shader = LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(),
            currentColors, null, Shader.TileMode.CLAMP
        )
        paint.shader = shader
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }
}