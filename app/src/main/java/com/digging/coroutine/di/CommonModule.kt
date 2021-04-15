package com.digging.coroutine.di

import com.digging.coroutine.base.DefaultDispatcherProvider
import com.digging.coroutine.base.DispatcherProvider
import org.koin.dsl.module

val commonModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}