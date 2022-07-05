package com.budi.setiawan.core.data.remote

import com.budi.setiawan.core.data.source.remote.response.ListGameResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListGameResponseTest {
    @Test
    fun testEquals() {
        val listGameResponse1 = ListGameResponse(
            2,
            emptyList()
        )
        val listGameResponse2 = ListGameResponse(
            2,
            emptyList()
        )

        Assert.assertTrue(listGameResponse1 == listGameResponse2 && listGameResponse2 == listGameResponse1)
        Assert.assertTrue(listGameResponse1.hashCode() == listGameResponse2.hashCode())
    }
}