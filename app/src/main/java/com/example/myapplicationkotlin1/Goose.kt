package com.example.myapplicationkotlin1

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF

class Goose (res: Resources, var x: Float, var y: Float, size: Int): TickListener, Comparable<Goose> {
    private val bound: RectF = RectF(x, y, x + size, y + size)
    private var gooseImg: Bitmap = BitmapFactory.decodeResource(res, R.drawable.test_goose_icon)
    private var flyGooseImg: Bitmap = BitmapFactory.decodeResource(res, R.drawable.test_fly_duck)
    private var pressed = false

    init {
        gooseImg = Bitmap.createScaledBitmap(gooseImg, size, size, true)
        flyGooseImg = Bitmap.createScaledBitmap(flyGooseImg, size, size, true)
    }

    override fun tick() {
        dance()
    }

    fun setPressedStatus(pressed: Boolean){
        this.pressed = pressed
    }

    fun dance(){
        val dx = (Math.random() * 10 - 5).toFloat()
        val dy = (Math.random() * 10 - 5).toFloat()
        //println("dx = $dx, dy = $dy")
        bound.offset(dx, dy)
    }

    fun drawGoose(c: Canvas){
        if (pressed){
            c.drawBitmap(flyGooseImg, bound.left , bound.top, null)
        }
        else {
            c.drawBitmap(gooseImg, bound.left, bound.top, null)
        }
    }

    fun contains(x: Float, y: Float): Boolean{
        return bound.contains(x, y)
    }

    override fun compareTo(other: Goose): Int {
        return (other.bound.top - this.bound.top).toInt()
    }
}