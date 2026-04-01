package com.jh.rethinkvpncontroller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShortcutActivity : AppCompatActivity() {
    private val rethinkPackage = "com.celzero.bravedns"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (intent.action) {
            "ACTION_VPN_START" -> sendVpnIntent(true)
            "ACTION_VPN_STOP" -> sendVpnIntent(false)
        }

        // Finish immediately - this is a trampoline activity
        finish()
    }

    private fun sendVpnIntent(isStart: Boolean) {
        val action = if (isStart) {
            "com.celzero.bravedns.intent.action.VPN_START"
        } else {
            "com.celzero.bravedns.intent.action.VPN_STOP"
        }

        val intent = Intent().apply {
            this.action = action
            setPackage(rethinkPackage)
            putExtra("sender", packageName)
        }

        sendBroadcast(intent)
    }
}
