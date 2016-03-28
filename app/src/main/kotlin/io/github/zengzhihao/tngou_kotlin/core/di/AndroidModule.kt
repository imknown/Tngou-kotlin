package io.github.zengzhihao.tngou_kotlin.core.di

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ApplicationScope
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ClientVersionCode
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ClientVersionName
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication
import io.github.zengzhihao.tngou_kotlin.utils.Preconditions
import timber.log.Timber

/**
 * Created by kela.king on 16/3/28.
 */
@Module
class AndroidModule {
    private val _context: Context

    constructor(application: Application) {
        _context = Preconditions.checkNotNull(application, "application context can't be null.")
    }

    @Provides
    @ApplicationScope
    @ForApplication
    fun provideApplicationContext() = _context

    @ApplicationScope
    @Provides
    @ClientVersionCode
    fun provideVersionCode(@ForApplication context: Context): Int {
        return _getVersionCode(context)
    }

    @ApplicationScope
    @Provides
    @ClientVersionName
    fun provideVersionName(@ForApplication context: Context): String {
        return _getVersionName(context)
    }

    @ApplicationScope
    @Provides
    fun provideAssetManager(@ForApplication context: Context): AssetManager {
        return context.assets
    }

    private fun _getVersionCode(context: Context): Int {
        val pi = _getPackageInfo(context)
        if (pi != null) {
            return pi.versionCode
        }
        return 0
    }

    private fun _getVersionName(context: Context): String {
        val pi = _getPackageInfo(context)
        if (pi != null) {
            return pi.versionName
        } else {
            return ""
        }
    }

    private fun _getPackageInfo(context: Context): PackageInfo? {
        val pm = context.packageManager
        var pi: PackageInfo? = null
        try {
            pi = pm.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e, "get package manager failed")
        }
        return pi
    }
}