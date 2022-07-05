package com.bui.setiawan.gamein.factory

import java.util.*

class DataFactory {

    companion object Factory {

        fun randomString(): String {
            return UUID.randomUUID().toString()
        }

        fun randomInt(): Int {
            return Random().nextInt()
        }

        fun randomDouble(): Double {
            return Random().nextDouble()
        }

        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }
    }
}