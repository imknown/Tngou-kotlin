/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import butterknife.bindView
import com.squareup.picasso.Picasso
import io.github.zengzhihao.tngou_kotlin.R
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ClientVersionName
import io.github.zengzhihao.tngou_kotlin.lib.api.service.TopService
import io.github.zengzhihao.tngou_kotlin.ui.base.AbstractActivity
import io.github.zengzhihao.tngou_kotlin.utils.ToastHelper
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @author Kela.King
 */
class TopActivity : AbstractActivity() {

    @Inject
    lateinit var _topService: TopService
    @Inject
    lateinit var _picasso: Picasso
    @Inject
    lateinit var _toastHelper: ToastHelper
    @field:[Inject ClientVersionName]
    lateinit var _versionName: String

    val _listView by bindView<ListView>(R.id.listView)

    private var _topAdapter: TopAdapter by Delegates.notNull<TopAdapter>()

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        _topAdapter = TopAdapter(this, _picasso)
        _listView.adapter = _topAdapter

        bind(_topService.list())
                .then {
                    _topAdapter.setResult(it.tngou)
                    _toastHelper.show("Just fucking, never stop. Appversion is " + _versionName)
                }
                .fail {
                    Timber.e("### onError. error is $it")
                }
    }

    override fun injectMembers() {
        getComponent().inject(this)
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, TopActivity::class.java))
    }
}
