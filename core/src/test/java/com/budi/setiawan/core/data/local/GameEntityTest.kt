package com.budi.setiawan.core.data.local

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameEntityTest {

    /**
     * Test an equation for object symmetry
     */
    @Test
    fun testEqualsSymetric() {
        val game1 = GameEntity(
            1,
            "Name",
            "2022-07-05",
            "Background-Image",
            4.8,
            100,
            80,
            800,
            "Descriptions",
            "dicoding.com",
            "Genres",
            true,
        )
        val game2 = GameEntity(
            1,
            "Name",
            "2022-07-05",
            "Background-Image",
            4.8,
            100,
            80,
            800,
            "Descriptions",
            "dicoding.com",
            "Genres",
            true,
        )

        Assert.assertTrue(game1 == game2 && game2 == game1)
        Assert.assertTrue(game1.hashCode() == game2.hashCode())
    }
}