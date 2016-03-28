/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin

import android.content.Context
import com.facebook.stetho.Stetho
import io.github.zengzhihao.tngou_kotlin.core.di.*
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * @author Kela.King
 */
class Application : android.app.Application(), HasComponent<ApplicationComponent> {

    private var _component by Delegates.notNull<ApplicationComponent>()

    override fun onCreate() {
        super.onCreate()

        _setupObjectGraph()
        _setupAnalytics()
    }

    private fun _setupObjectGraph() {
        _component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule())
                .apiModule(ApiModule())
                .utilsModule(UtilsModule())
                .build()
    }

    private fun _setupAnalytics() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())
        }
    }

    override fun getComponent(): ApplicationComponent {
        return _component
    }

    companion object {
        fun from(context: Context) = context.applicationContext as Application
    }
}
