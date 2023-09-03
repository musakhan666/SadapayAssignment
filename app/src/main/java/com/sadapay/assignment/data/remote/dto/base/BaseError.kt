package com.sadapay.assignment.data.remote.dto.base

data class BaseError(
    var message: String ?= null,
) {
    fun getMessages() = message

}