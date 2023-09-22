package com.example.myapplicationkotlin1

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random


class MyView(c: Context): View(c), TickListener {
    // paint
    private val paint1 = Paint()
    private val paint2 = Paint()
    // late init not allow for primitive types
    private var screenWidth: Float = 0f
    private var screenHeight: Float = 0f
    private val interactiveGooseList: ArrayList<Goose> = ArrayList()
    private var firstTime = true
    private val timer = Timer(Looper.getMainLooper())
    private var gooseSize = 0
    // play music
    private var mediaPlayer = MediaPlayer.create(c, R.raw.mangojump_nine_and_eighteen)

    init {
        // draw a circle
        paint1.color = Color.RED
        paint2.color = Color.BLUE
        paint1.strokeWidth = 10f
        mediaPlayer.start()
        mediaPlayer.isLooping = true
    }

    override fun tick() {
        invalidate()
    }

    private fun makeGoose() {
        for (i in 1 .. 20) {
            val rx = Random.nextInt(gooseSize, (width - gooseSize)).toFloat()
            val ry = Random.nextInt(gooseSize, (height - gooseSize)).toFloat()
            val goose = Goose(resources, rx, ry, gooseSize)
            interactiveGooseList.add(goose)
            timer.registerTickListener(goose)
        }

    }

    fun pause(){
        mediaPlayer.pause()
        timer.pauseSwitch(true)
    }

    fun resume(){
        mediaPlayer.start()
        timer.pauseSwitch(false)
    }

    fun release(){
        mediaPlayer.release()
        mediaPlayer = null // this is not necessary, but recommended
    }

    override fun onDraw(canvas: Canvas) {
        if (firstTime){
            //super.onDraw(canvas)
            screenWidth = width.toFloat()
            screenHeight = height.toFloat()
            println("screenWidth = $screenWidth, screenHeight = $screenHeight")
            gooseSize = (screenWidth * 0.2f).toInt()
            makeGoose()
            timer.registerTickListener(this)

            firstTime = false
        }
        interactiveGooseList.sort()
        for (goose in interactiveGooseList) {
            goose.drawGoose(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //return super.onTouchEvent(event)
        val tappedGoose: ArrayList<Goose> = ArrayList()
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                //println("ACTION_DOWN")
                Log.d("MyView", "ACTION_DOWN")
                for (goose in interactiveGooseList.reversed()) {
                    if (goose.contains(event.x, event.y)){
                        tappedGoose.add(goose)
                        //interactiveGooseList.remove(goose)
                        timer.unregisterTickListener(goose)
                        //break
                        // change pressed status
                        //goose.setPressedStatus(true)
                        //// repaint
                        //invalidate()
                        //Toast.makeText(context, "goose touched", Toast.LENGTH_SHORT).show()
                        //println("!!!!goose touched!!!!")
                    }
                }
                interactiveGooseList.removeAll(tappedGoose)
                invalidate()

                if (interactiveGooseList.size == 0) {
                    val adb: AlertDialog.Builder = AlertDialog.Builder(context)
                    adb.setTitle("Game Over")
                    adb.setMessage("鴨子滅絕了，你要更多鴨子嗎")
                    adb.setCancelable(false)
                    adb.setPositiveButton("Yes"
                    ) { _, _ ->
                        makeGoose()
                        invalidate()
                    }
                    adb.setNegativeButton("No"
                    ) { _, _ -> (context as Activity).finish() }
                    adb.create().show()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                //println("ACTION_MOVE")
                Log.d("MyView", "ACTION_MOVE")
            }
            MotionEvent.ACTION_UP -> {
                //println("ACTION_UP")
                Log.d("MyView", "ACTION_UP")
                for (goose in interactiveGooseList) {
                    if (goose.contains(event.x, event.y)){
                        // change pressed status
                        goose.setPressedStatus(false)
                        // repaint
                        invalidate()
                        Toast.makeText(context, "goose released", Toast.LENGTH_SHORT).show()
                        println("!!!!goose released!!!!")
                    }
                }
            }
        }
        return true
    }
}