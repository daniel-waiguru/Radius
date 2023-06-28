package com.danielwaiguru.radius.common.di

import com.danielwaiguru.radius.common.utils.Dispatcher
import com.danielwaiguru.radius.common.utils.RadiusDispatchers.IO
import com.danielwaiguru.radius.common.utils.RadiusDispatchers.MAIN
import com.danielwaiguru.radius.common.utils.RadiusDispatchers.UNCONFINED
import com.danielwaiguru.radius.common.utils.RadiusDispatchers.DEFAULT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(UNCONFINED)
    fun providesUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}