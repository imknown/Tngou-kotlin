package io.github.zengzhihao.tngou_kotlin.core.di

import io.github.zengzhihao.tngou_kotlin.ui.HomeActivity
import io.github.zengzhihao.tngou_kotlin.ui.top.TopActivity

/**
 * Created by kela.king on 16/3/28.
 */
interface ViewInjector {
    fun inject(homeActivity: HomeActivity)
    fun inject(topActivity: TopActivity)
}