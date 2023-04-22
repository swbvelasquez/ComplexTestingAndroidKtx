package com.swbvelasquez.complextestingandroidktx.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItemTable")
data class ShoppingItem (
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,
    var name:String,
    var amount:Int,
    var price:Double,
    var imageUrl: String)