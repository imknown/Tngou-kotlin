package io.github.zengzhihao.tngou_kotlin.core.di

import android.content.Context
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.core.EventBus
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ApplicationScope
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication

/**
 * Created by kela.king on 16/3/28.
 */
@Module
class DataModule {
    @Provides
    @ApplicationScope
    fun provideSharedPreferences(@ForApplication context: Context) = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideEventBus(): Bus = EventBus.newInstance()
}