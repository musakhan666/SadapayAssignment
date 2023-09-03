package com.sadapay.assignment.data.remote.dto.base

import com.google.gson.*
import com.sadapay.assignment.utils.toDateFromApi
import com.sadapay.assignment.utils.toStringForApi
import org.joda.time.DateTime
import java.lang.reflect.Type

object DateTimeTypeAdapter : JsonDeserializer<DateTime>, JsonSerializer<DateTime> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DateTime {
        return json.toString().toDateFromApi()
    }

    override fun serialize(
        src: DateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.toStringForApi()?:"")
    }

}
