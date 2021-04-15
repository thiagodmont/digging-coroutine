package com.digging.coroutine.di

import com.digging.coroutine.BuildConfig
import com.digging.coroutine.base.RetrofitBuilder
import org.koin.dsl.module

val networkModule = module {
    single(qualifier = ApiNewsQualifier) {
        RetrofitBuilder().host(BuildConfig.URL_NEWS_API).build()
    }
}