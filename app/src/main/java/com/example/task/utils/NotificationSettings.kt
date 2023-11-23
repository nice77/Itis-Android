package com.example.task.utils

import android.os.Build

object NotificationSettings {
    var versionHigherThanTiramisu : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    var isAllowed : Boolean = false
    var importantLevel : Int = 0
    var privacyLevel : Int = 0
    var expanding : Boolean = false
    var buttonsShowUp : Boolean = false
}