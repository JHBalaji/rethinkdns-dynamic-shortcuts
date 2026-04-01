package com.jh.rethinkvpncontroller

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val rethinkPackageName = "com.celzero.bravedns"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize dynamic shortcuts
        setupDynamicShortcuts()

        // Setup UI buttons
        findViewById<Button>(R.id.btnStartVpn).setOnClickListener {
            sendVpnIntent(isStart = true)
        }

        findViewById<Button>(R.id.btnStopVpn).setOnClickListener {
            sendVpnIntent(isStart = false)
        }

        // Check if RethinkDNS is installed
        if (!isRethinkInstalled()) {
            showRethinkNotInstalledDialog()
        }
    }

    private fun sendVpnIntent(isStart: Boolean) {
        val action = if (isStart) {
            "com.celzero.bravedns.intent.action.VPN_START"
        } else {
            "com.celzero.bravedns.intent.action.VPN_STOP"
        }

        val intent = Intent().apply {
            this.action = action
            setPackage(rethinkPackageName)
            putExtra("sender", packageName)
        }

        try {
            sendBroadcast(intent)
            showToast(if (isStart) "VPN Start sent" else "VPN Stop sent")
        } catch (e: Exception) {
            showToast("Error: ${e.message}")
        }
    }

    private fun isRethinkInstalled(): Boolean {
        return try {
            packageManager.getPackageInfo(rethinkPackageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun setupDynamicShortcuts() {
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val startShortcut = ShortcutInfo.Builder(this, "vpn_start")
            .setShortLabel("Start VPN")
            .setLongLabel("Start RethinkDNS VPN")
            .setIcon(Icon.createWithResource(this, R.drawable.ic_vpn_start))
            .setIntent(Intent(this, ShortcutActivity::class.java).apply {
                action = "ACTION_VPN_START"
            })
            .build()

        val stopShortcut = ShortcutInfo.Builder(this, "vpn_stop")
            .setShortLabel("Stop VPN")
            .setLongLabel("Stop RethinkDNS VPN")
            .setIcon(Icon.createWithResource(this, R.drawable.ic_vpn_stop))
            .setIntent(Intent(this, ShortcutActivity::class.java).apply {
                action = "ACTION_VPN_STOP"
            })
            .build()

        shortcutManager?.dynamicShortcuts = listOf(startShortcut, stopShortcut)
    }

    private fun showRethinkNotInstalledDialog() {
        AlertDialog.Builder(this)
            .setTitle("RethinkDNS Not Installed")
            .setMessage("This app requires RethinkDNS to be installed. Please install it from the Play Store.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
