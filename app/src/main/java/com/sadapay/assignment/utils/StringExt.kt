package com.sadapay.assignment.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

fun String.toDateFromApi(): DateTime = DateTimeFormat.forPattern(
    if (this.contains(".")) "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    else "yyyy-MM-dd'T'HH:mm:ssZ"
).parseDateTime(this + if (!this.contains("+")) "+0000" else "").withZone(DateTimeZone.getDefault())

fun DateTime.toStringForApi(): String =
    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").print(this.withZone(DateTimeZone.UTC))
