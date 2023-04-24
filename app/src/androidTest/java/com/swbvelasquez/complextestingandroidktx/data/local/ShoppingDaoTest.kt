package com.swbvelasquez.complextestingandroidktx.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.swbvelasquez.complextestingandroidktx.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi //Lo solicita los test con runTest por el momento
@RunWith(AndroidJUnit4::class)
@SmallTest  //Existen los smalltest: pruebas unitarias, medium test: pruebas de integracion, large test: pruebas de ui o contextos
class ShoppingDaoTest {

    @get:Rule //Se le indica reglas de comportamiento a JUnit para esta clase
    var instantTaskExecutorRule = InstantTaskExecutorRule() // Indica una regla para especificar a JUnit que ejecute el codigo uno tras otro en el mismo hilo

    private lateinit var database: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun onBefore(){
        database = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ShoppingDatabase::class.java) //Crea una base de datos pero en memoria
            .allowMainThreadQueries() //Permite que se escriba y lea en el hilo principal
            .build()

        shoppingDao = database.getShoppingDao()
    }

    @After
    fun onAfter(){
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest { //runTest = similar a runBlocking
        val shoppingItem = ShoppingItem(name="name", amount=1,price=1.0, imageUrl = "image", id = 1)
        shoppingDao.insertShoppingItem(shoppingItem)

        val allItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue() //getOrWaiatedValue es una funcion de extension de la clase LiveDataUtilAndroidTest.kt, sirve para retornar el valor de un liveData en los test sin usar corutinas

        assertThat(allItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val shoppingItem = ShoppingItem(name="name", amount=1,price=1.0, imageUrl = "image", id = 1)
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)

        val allItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runTest {
        val shoppingItem1 = ShoppingItem(name="name", amount=5,price=5.0, imageUrl = "image", id = 1)
        val shoppingItem2 = ShoppingItem(name="name", amount=10,price=2.0, imageUrl = "image", id = 2)
        val shoppingItem3 = ShoppingItem(name="name", amount=1,price=25.0, imageUrl = "image", id = 3)

        shoppingDao.insertShoppingItem(shoppingItem1)
        shoppingDao.insertShoppingItem(shoppingItem2)
        shoppingDao.insertShoppingItem(shoppingItem3)

        val totalSum = shoppingDao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalSum).isEqualTo(70.0)
    }
}