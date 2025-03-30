package com.example.backendkotlin.utils

/**
 * Thread.sleepをラップするユーティリティクラス
 */
object SleepUtil {
    /**
     * 指定されたミリ秒だけスリープする
     *
     * @param millis スリープする時間（ミリ秒）
     */
    fun threadSleep(millis: Long) {
        require(millis >= 0) { "Sleep time must be greater than 0" }
        Thread.sleep(millis)
    }
}
