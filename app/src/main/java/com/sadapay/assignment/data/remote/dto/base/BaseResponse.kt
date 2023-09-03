package com.sadapay.assignment.data.remote.dto.base

import javax.annotation.Nullable

open class BaseResponse<T>(
    var status: String? = null,
    var error: BaseError? = null,
    var message: String? = null,
    @Nullable var body: T? = null,
    var xDeviceId: String? = null
) {

    fun getErrorMessages() = getMessages() ?: message
    fun getMessages() = error?.message

    fun getBody() = body?.toString()

}