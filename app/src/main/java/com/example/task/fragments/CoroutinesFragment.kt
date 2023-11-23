package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.task.MainActivity
import com.example.task.R
import com.example.task.databinding.FragmentCoroutinesBinding
import com.example.task.utils.CoroutinesSettings
import com.example.task.utils.CoroutinesSettings.jobsList
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CoroutinesFragment : Fragment(R.layout.fragment_coroutines) {

    private var binding : FragmentCoroutinesBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoroutinesBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoroutinesBinding.bind(view)
        binding?.let {binding ->

            binding.bgCb.setOnCheckedChangeListener { _, b ->
                CoroutinesSettings.isStoppedOnBackground = b
            }
            binding.asyncCb.setOnCheckedChangeListener { _, b ->
                CoroutinesSettings.isAsync = b
            }

            binding.execBtn.setOnClickListener {
                requireActivity().lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        println("${CoroutinesSettings.isCollapsed} ${CoroutinesSettings.isStoppedOnBackground}")
                        for (i in 0 until binding.displaySb.progress) {
                            if (CoroutinesSettings.isCollapsed
                                && CoroutinesSettings.isStoppedOnBackground
                                && !CoroutinesSettings.isAsync) {
                                CoroutinesSettings.cancelAll()
                                return@withContext
                            }
                            if (binding.asyncCb.isChecked) {
                                jobsList.add(async(start=CoroutineStart.LAZY) { getRandomNumber(i) })
                            }
                            else {
                                jobsList.add(async { getRandomNumber(i) }.apply { await() })
                            }
                        }
                        println("Total: ${CoroutinesSettings.isCollapsed} ${CoroutinesSettings.isStoppedOnBackground}")
                        if (binding.asyncCb.isChecked) {
                            jobsList.awaitAll()
                        }
                        CoroutinesSettings.outputAll()
                        jobsList.clear()
                    }
                }
            }
        }
    }

    suspend fun getRandomNumber(pos: Int) : Int {
        println("Started: $pos")
        delay(3000)
        return (Math.random() * 100).toInt()
    }
}