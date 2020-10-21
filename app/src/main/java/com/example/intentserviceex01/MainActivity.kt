package com.example.intentserviceex01

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startService(view: View) {

        val serviceIntent = Intent(this, MyIntentService::class.java)
        serviceIntent.putExtra("logName", "MAIN_ACTIVITY")
        serviceIntent.putExtra(MyIntentService.BUNDLED_LISTENER, object : ResultReceiver(Handler()) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                super.onReceiveResult(resultCode, resultData)
                if (resultCode == RESULT_OK) {
                    val value = resultData.getString("value")
                    changeText(value)
                } else {
                    changeText("99")
                }
            }
        })
        startService(serviceIntent)
    }

    fun changeText(value: String?) {
        textView.setText(value.toString())
    }

}