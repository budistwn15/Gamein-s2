package com.budi.setiawan.core.factory

import java.util.*

class FakerFactory {
    companion object Factory {
        fun fakerString(): String {
            return UUID.randomUUID().toString()
        }

        fun fakerInt(): Int {
            return Random().nextInt()
        }

        fun fakerDouble(): Double {
            return Random().nextDouble()
        }

        fun fakerBoolean(): Boolean {
            return Math.random() < 0.5
        }
    }
}
