package com.example.myapplicationkotlin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock


class MainActivity : AppCompatActivity() {

    private lateinit var myView: MyView
    //lateinit var textView: TextView
    //var counter = 0
    //var handler = Handler(Looper.getMainLooper())
    //val lock = ReentrantLock()

    //companion object{
    //    val countDown: CountDownLatch = CountDownLatch(1)
    //
    //}

    //inner class AddOne: Runnable {
    //    override fun run() {
    //        // counter++
    //        lock.lock()
    //        counter++
    //        lock.unlock()
    //        // let main thread update UI
    //        handler.post {
    //            textView.text = counter.toString()
    //        }
    //    }
    //}
    //
    //override fun onPause() {
    //    super.onPause()
    //    // pause music
    //    myView.pause()
    //}
    //
    //override fun onResume() {
    //    super.onResume()
    //    // resume music
    //    myView.resume()
    //}
    //
    //override fun onDestroy() {
    //    // release music
    //    myView.release()
    //    super.onDestroy()
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View group
        val linearLayout = LinearLayout(this)
        val button = Button(this)
        button.text = "Send message"
        button.setOnClickListener {
            //val intent = Intent(this, SecondActivity::class.java)
            //startActivity(intent)
            val intent = Intent() // implicit intent
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Hello from my app")
            intent.type = "text/plain"
            try {
                startActivity(intent)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        val textView: TextView = TextView(this)
        textView.text = "This is the first view"
        linearLayout.addView(button)
        linearLayout.addView(textView)
        setContentView(linearLayout)

        //myView = MyView(this)
        //setContentView(myView)


        //setContentView(R.layout.activity_main)
        //
        //val button: Button = findViewById(R.id.button)
        //textView = findViewById(R.id.textView)

        ////initiate other thread and wait for it to be ready
        //val otherThread = OtherThread()
        //otherThread.start()
        //try {
        //    countDown.await()
        //} catch (e: InterruptedException) {
        //    e.printStackTrace()
        //}

        //// anonymous class
        //button.setOnClickListener(View.OnClickListener {
        //    Thread(AddOne()).start()
        //    //Log.d("button to start thread", "button clicked!")
        //    //otherThread.myHandler.sendMessage(Message.obtain())
        //    //otherThread.myHandler.updateUI(textView)
        //})
    }
}