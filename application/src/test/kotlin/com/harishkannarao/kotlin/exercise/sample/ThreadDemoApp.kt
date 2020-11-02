package com.harishkannarao.kotlin.exercise.sample

object ThreadDemoApp {

    @JvmStatic
    fun main(args: Array<String>) {
        println(Thread.currentThread().name)
        0.until(10).forEach {
            object : Thread("$it") {
                override fun run() {
                    sleep(1L * 1000L)
                    println(currentThread().name)
                }
            }.start()
        }
    }
}