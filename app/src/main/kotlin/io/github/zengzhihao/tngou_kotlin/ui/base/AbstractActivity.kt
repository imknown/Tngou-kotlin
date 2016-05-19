/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.base

import android.os.Bundle
import com.github.zengzhihao.rxpromise.Promise
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import io.github.zengzhihao.tngou_kotlin.Application
import io.github.zengzhihao.tngou_kotlin.core.di.ApplicationComponent
import io.github.zengzhihao.tngou_kotlin.core.di.HasComponent
import io.github.zengzhihao.tngou_kotlin.core.di.Injectable
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author Kela.King
 */
abstract class AbstractActivity : RxAppCompatActivity(), HasComponent<ApplicationComponent>, Injectable {

    protected fun <T> bind(observable: Observable<T>) =
            Promise(observable.compose(this.bindToLifecycle<T>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()))

    protected fun <T> bind(observable: Observable<T>, activityEvent: ActivityEvent) =
            Promise(observable.compose(this.bindUntilEvent<T>(activityEvent))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMembers()
    }

    override fun getComponent(): ApplicationComponent {
        return Application.from(this).getComponent()
    }
}
