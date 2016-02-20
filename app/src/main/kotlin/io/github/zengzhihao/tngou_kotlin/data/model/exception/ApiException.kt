/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.data.model.exception

import retrofit.RetrofitError

/**
 * @author Kela.King
 */
open class ApiException : RuntimeException {

    constructor(throwable: Throwable?) : super(throwable)

    constructor(detailMessage: String, throwable: Throwable?) : super(detailMessage, throwable)

    companion object {
        fun create(retrofitError: RetrofitError): ApiException {
            val throwable = retrofitError.cause

            when (retrofitError.kind.ordinal) {
                0 -> return NetworkException("network error", throwable)

                1 -> return GsonConversionException("gson convert error", throwable)

                2 -> {
                    try {
                        val apiError = retrofitError.getBodyAs(ApiError::class.java)

                        if (apiError is ApiError) {
                            return ServerException("server response error:" + " " + apiError.toString(), throwable, apiError)
                        } else {
                            return UnexpectedException("unexpected error", throwable)
                        }
                    } catch(e: Exception) {
                        return UnexpectedException("unexpected error", throwable)
                    }
                }

                else -> return UnexpectedException("unexpected error", throwable)
            }
        }
    }
}
