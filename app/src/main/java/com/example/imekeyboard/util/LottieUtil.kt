package com.example.imekeyboard.util

import com.example.imekeyboard.LottieLog

object LottieUtil {
    @JvmStatic
    var all = HashMap< Long , ArrayList<LottieLog>>()

    var allRV = HashMap<Long , String>()

    var windowShown : Boolean = false

    fun set(long: Long, log: ArrayList<LottieLog>){
        all[long] = log
    }
}