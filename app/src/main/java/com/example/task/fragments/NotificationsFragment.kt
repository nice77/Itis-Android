package com.example.task.fragments

import android.app.Notification
import android.app.NotificationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.task.R
import com.example.task.databinding.FragmentNotificationsBinding
import com.example.task.utils.NotificationSettings

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private var binding : FragmentNotificationsBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)
        println("onCreateView is called inside the NotificationsFragment")
        binding?.let { binding ->
            binding.radioImpGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    radioGroup[0].id -> NotificationSettings.importantLevel = NotificationManager.IMPORTANCE_LOW
                    radioGroup[1].id -> NotificationSettings.importantLevel = NotificationManager.IMPORTANCE_DEFAULT
                    radioGroup[2].id -> NotificationSettings.importantLevel = NotificationManager.IMPORTANCE_HIGH
                }
            }
            binding.radioPrivacyGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    radioGroup[0].id -> NotificationSettings.privacyLevel = Notification.VISIBILITY_PUBLIC
                    radioGroup[1].id -> NotificationSettings.privacyLevel = Notification.VISIBILITY_SECRET
                    radioGroup[2].id -> NotificationSettings.privacyLevel = Notification.VISIBILITY_PRIVATE
                }
            }
            binding.expandingCb.setOnCheckedChangeListener { _, b ->
                NotificationSettings.expanding = b
            }
            binding.buttonsCb.setOnCheckedChangeListener { _, b ->
                NotificationSettings.buttonsShowUp = b
            }
        }
    }

}