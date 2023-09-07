package com.sadapay.assignment.data.remote.dto.base

import com.google.gson.annotations.SerializedName


open class BaseResponse<T>(
    var status: String? = null,
    var error: BaseError? = null,
    var message: String? = null,
    var items: List<T>? = ArrayList(),
    @SerializedName("total_count") var total: Int = 0
) {

    fun getErrorMessages() = getMessages() ?: message
    fun getMessages() = error?.message

}