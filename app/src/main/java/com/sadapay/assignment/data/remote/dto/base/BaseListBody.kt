package com.sadapay.assignment.data.remote.dto.base

import com.google.gson.annotations.SerializedName

open class BaseListBody<T>(
    var items: List<T>? = ArrayList(),
    @SerializedName("total_count")
    var total: Int = 0,
    var size: Int = 0,
    @SerializedName("totalPages")
    var number: Int = 0
)
