package com.example.myapplication.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.CenterRepository
import com.example.myapplication.data.CenterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Share preference
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharePreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("FilmAppPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application) : AppDatabase {
        return AppDatabase.getInstance(app)
    }

    @Provides
    @Singleton
    fun provideCenterRepository(appDatabase: AppDatabase) : CenterRepository {
        return CenterRepositoryImpl(appDatabase.centerDao)
    }
}