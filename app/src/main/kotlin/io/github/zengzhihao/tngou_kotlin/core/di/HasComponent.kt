package io.github.zengzhihao.tngou_kotlin.core.di

/**
 * Created by kela.king on 16/3/28.
 */
interface HasComponent<C> {
    fun getComponent(): C
}