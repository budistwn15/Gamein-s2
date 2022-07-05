package com.budi.setiawan.core.data.remote

import com.budi.setiawan.core.data.source.remote.response.GenreResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GenreResponseTest {
    @Test
    fun testEquals() {
        val genreResponse1 = GenreResponse(
            1,
            "Name",
        )
        val genreResponse2 = GenreResponse(
            1,
            "Name",
        )

        Assert.assertTrue(genreResponse1 == genreResponse2 && genreResponse2 == genreResponse1)
        Assert.assertTrue(genreResponse1.hashCode() == genreResponse2.hashCode())
    }
}