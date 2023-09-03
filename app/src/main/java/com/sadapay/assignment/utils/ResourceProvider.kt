package com.sadapay.assignment.utils

import android.content.ContentResolver
import android.content.Context
import android.os.Environment
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)
    fun getString(@StringRes resId: Int, value: String): String = context.getString(resId, value)
    fun getDirectory() = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    fun getContentResolver(): ContentResolver = context.contentResolver
    fun getContext() = context


}