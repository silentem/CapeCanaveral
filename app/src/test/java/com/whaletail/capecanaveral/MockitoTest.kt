package com.whaletail.capecanaveral

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.*
import org.junit.Assert.*


open class User(val name: String, val surname: String)

@RunWith(MockitoJUnitRunner::class)
class MockitoTest {


    @Mock
    lateinit var user: User

    val list = mutableListOf<Int>()

    val spyList = spy(list)

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Test
    fun testMockito() {

        `when`(user.name).thenReturn("Artem")

        assertEquals(user.name, "Artem")
        assertEquals(user.surname, null)

        doReturn(1).`when`(spyList)[0]

        assertEquals(spyList[0], 1)


    }


}