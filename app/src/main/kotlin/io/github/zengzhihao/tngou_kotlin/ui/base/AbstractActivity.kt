/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.base

import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author Kela.King
 */
open class AbstractActivity : RxAppCompatActivity() {

    protected fun <T> bind_(observable: Observable<T>) =
            observable.compose(this.bindToLifecycle<T>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    protected fun <T> bind_(observable: Observable<T>, activityEvent: ActivityEvent) =
            observable.compose(this.bindUntilEvent<T>(activityEvent))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}
