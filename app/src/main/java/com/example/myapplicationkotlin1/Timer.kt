package com.example.myapplicationkotlin1

import android.os.Handler
import android.os.Looper
import android.os.Message

class Timer(looper: Looper): Handler(looper) {
    private val listeners: ArrayList<TickListener> = ArrayList()
    private var paused: Boolean = false

    init {
        // send message to current looper's MQ
        sendMessageDelayed(Message.obtain(), 0)
    }

    fun registerTickListener(t: TickListener){
        // register listener
        listeners.add(t)
    }

    fun unregisterTickListener(t: TickListener){
        // unregister listener
        listeners.remove(t)
    }

    private fun notifyTickListeners(){
        // notify all listeners
        for (l in listeners){
            l.tick()
        }
    }

    fun pauseSwitch(boolean: Boolean){
        paused = boolean
    }

    override fun handleMessage(msg: Message) {
        //super.handleMessage(msg)
        if (!paused){
            notifyTickListeners()
        }

        sendMessageDelayed(Message.obtain(), 100)
    }
}