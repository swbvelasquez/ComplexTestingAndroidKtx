package com.swbvelasquez.complextestingandroidktx.di

import android.content.Context
import androidx.room.Room
import com.swbvelasquez.complextestingandroidktx.data.local.ShoppingDatabase
import com.swbvelasquez.complextestingandroidktx.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,ShoppingDatabase::class.java,Constants.DATABASE_NAME)

    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingDatabase) = database.getShoppingDao()

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}