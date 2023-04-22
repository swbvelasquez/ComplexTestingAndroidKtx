package com.swbvelasquez.complextestingandroidktx.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest  //Existen los smalltest: pruebas unitarias, medium test: pruebas de integracion, large test: pruebas de ui o contextos
class ShoppingDaoTest {
    private lateinit var database: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun onBefore(){

    }

    @After
    fun onAfter(){

    }
}