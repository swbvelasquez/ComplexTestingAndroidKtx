package com.swbvelasquez.complextestingandroidktx.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(item:ShoppingItem):Long

    @Delete
    suspend fun deleteShoppingItem(item:ShoppingItem):Int

    @Query("select * from ShoppingItemTable")
    fun observeAllShoppingItems() : LiveData<List<ShoppingItem>>

    @Query("select sum(price*amount) from ShoppingItemTable")
    fun observeTotalPrice(): LiveData<Double>
}