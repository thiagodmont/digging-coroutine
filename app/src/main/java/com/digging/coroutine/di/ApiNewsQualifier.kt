package com.digging.coroutine.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ApiNewsQualifier : Qualifier {
    override val value: QualifierValue
        get() = "ApiNewsQualifier"
}