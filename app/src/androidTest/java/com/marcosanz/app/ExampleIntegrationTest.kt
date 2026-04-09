package com.marcosanz.app

import com.google.common.truth.Truth.assertThat
import com.marcosanz.app.di.TimberTestRule
import org.junit.Rule
import org.junit.Test

class ExampleIntegrationTest {

    @get:Rule
    var timberTestRule = TimberTestRule()


    @Test
    fun addition_isCorrect() {
        val result = 2.plus(2)
        val expected = 4
        assertThat(result).isEqualTo(expected)
    }
}
