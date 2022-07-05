package com.budi.setiawan.core.data.remote

import com.budi.setiawan.core.data.source.remote.response.GameResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameResponseTest {

    @Test
    fun testEquals() {
        val gameResponse1 = GameResponse(
            1,
            "Name",
            "2022-07-05",
            "Background-Image",
            4.8,
            100,
            90,
            100,
            "Description",
            "dicoding.com",
            emptyList()
        )
        val gameResponse2 = GameResponse(
            1,
            "Name",
            "2022-07-05",
            "Background-Image",
            4.8,
            100,
            90,
            100,
            "Description",
            "dicoding.com",
            emptyList()
        )

        Assert.assertTrue(gameResponse1 == gameResponse2 && gameResponse2 == gameResponse1)
        Assert.assertTrue(gameResponse1.hashCode() == gameResponse2.hashCode())
    }
}
