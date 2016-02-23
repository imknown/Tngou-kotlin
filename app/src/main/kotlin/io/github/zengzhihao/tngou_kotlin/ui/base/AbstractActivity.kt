/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.base

import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import io.github.zengzhihao.rxpromise.Promise
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author Kela.King
 */
open class AbstractActivity : RxAppCompatActivity() {

    protected fun <T> bind(observable: Observable<T>) =
            Promise(observable.compose(this.bindToLifecycle<T>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()))

    protected fun <T> bind(observable: Observable<T>, activityEvent: ActivityEvent) =
            Promise(observable.compose(this.bindUntilEvent<T>(activityEvent))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()))
}
