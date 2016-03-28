package io.github.zengzhihao.tngou_kotlin.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ApplicationScope
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication
import io.github.zengzhihao.tngou_kotlin.utils.ToastHelper

/**
 * Created by kela.king on 16/3/28.
 */
@Module
class UtilsModule {
    @Provides
    @ApplicationScope
    fun provideToastHelper(@ForApplication context: Context) = ToastHelper(context)
}