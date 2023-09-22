package com.example.myapplicationkotlin1
//
//import android.os.Handler
//import android.os.Looper
//import android.os.Message
//import android.widget.TextView
//
//class MyHandler(looper: Looper): Handler(looper) {
//    var counter = 0
//    override fun handleMessage(msg: Message) {
//        counter++
//    }
//    fun updateUI(view: TextView) {
//        // throw message to main thread
//        view.post {
//            view.text = counter.toString()
//        }
//    }
//}
//
//class OtherThread: Thread() {
//    lateinit var myHandler: MyHandler
//    override fun run() {
//        //super.run()
//        Looper.prepare()
//        //instantiate handler
//        myHandler = MyHandler(Looper.myLooper()!!)
//        MainActivity.countDown.countDown()
//
//        Looper.loop()
//    }
//}