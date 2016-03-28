package io.github.zengzhihao.tngou_kotlin.core.di

import dagger.Component
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ApplicationScope

/**
 * Created by kela.king on 16/3/28.
 */
@ApplicationScope
@Component(modules = arrayOf(AndroidModule::class, DataModule::class,
        NetworkModule::class, ApiModule::class, UtilsModule::class))
interface ApplicationComponent : ViewInjector {
    //        fun inject(application: Application)
}