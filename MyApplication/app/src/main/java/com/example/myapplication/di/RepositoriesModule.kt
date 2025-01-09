package com.example.myapplication.di

import com.example.myapplication.repositories.MainLog
import com.example.myapplication.repositories.MainLogImpl
import com.example.myapplication.repositories.StoreValue
import com.example.myapplication.repositories.StoreValueImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindMainLog(mainLog: MainLogImpl) : MainLog

    @Binds
    @Singleton
    abstract fun bindStoreValue(store : StoreValueImpl) :StoreValue
}