package com.example.intentserviceex01

import android.app.Activity
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import android.widget.Toast


// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.example.intentserviceex01.action.FOO"
private const val ACTION_BAZ = "com.example.intentserviceex01.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.intentserviceex01.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.intentserviceex01.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {
    var countProp : Int = 0
    var receiver: ResultReceiver? = null

    override fun onHandleIntent(intent: Intent?) {

        Log.d("MyService", "Service Started")

        while(countProp < 10){
            Log.d("MyService", "count: $countProp")
            countProp += 1

            val bundle = Bundle()
            bundle.putString("value", countProp.toString())
            receiver?.send(Activity.RESULT_OK, bundle)

            Thread.sleep(1000)
        }

        Log.d("MyService", "Ended count: $countProp")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val logName = intent!!.getStringExtra("logName")
        receiver = intent!!.getParcelableExtra<ResultReceiver>(BUNDLED_LISTENER)

        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        val BUNDLED_LISTENER = "listener"
    }
}
