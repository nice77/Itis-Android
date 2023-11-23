package com.example.task

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.task.utils.CoroutinesSettings
import com.example.task.utils.NotificationHandler
import com.example.task.utils.NotificationSettings
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CancellationException


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationHandler.initNotificationManager(this)
        requestPermission()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navbar)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        checkIntent(navController)
    }

    private fun checkIntent(navController : NavController) {
        println(intent.extras?.getString(NotificationHandler.ACTION_KEY))
        if (intent.extras?.getString(NotificationHandler.ACTION_KEY) == NotificationHandler.ACTION_VALUE_ONE) {
            navController.navigate(R.id.mainFragment)
            NotificationHandler.notificationManager.cancel(NotificationHandler.NOTIFICATION_ID)
            Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_SHORT).show()
            NotificationHandler.closeNotification()
        }
        else if (intent.extras?.getString(NotificationHandler.ACTION_KEY) == NotificationHandler.ACTION_VALUE_TWO) {
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navbar)
            bottomNavigationView.selectedItemId = R.id.notificationsFragment
            NotificationHandler.closeNotification()
        }
        intent.removeExtra(NotificationHandler.ACTION_KEY)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                POST_NOTIFICATIONS_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            POST_NOTIFICATIONS_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    NotificationSettings.isAllowed = true
                } else {
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                        requestPermission()
                    } else {
                        AlertDialog.Builder(this)
                            .setTitle(getString(R.string.dialog_title))
                            .setMessage(getString(R.string.dialog_message))
                            .setPositiveButton("Confirm") {d, w ->
                                val i = Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(
                                        "package:$packageName"
                                    )
                                )
                                startActivity(i)
                            }
                            .create()
                            .show()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        CoroutinesSettings.isCollapsed = true
        println("Called onStop: ${CoroutinesSettings.isCollapsed}")
        if (CoroutinesSettings.isAsync) {
            try {
                CoroutinesSettings.cancelAll()
            } catch (e : CancellationException) {
                println("Job is cancelled")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutinesSettings.isCollapsed = false
        println("Called onResume: ${CoroutinesSettings.isCollapsed}")
    }

    companion object {
        private const val POST_NOTIFICATIONS_CODE = 10
    }
}