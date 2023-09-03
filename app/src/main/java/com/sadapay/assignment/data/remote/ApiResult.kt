package com.sadapay.assignment.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sadapay.assignment.R
import com.sadapay.assignment.data.remote.dto.base.BaseError
import com.sadapay.assignment.data.remote.dto.base.BaseResponse
import okhttp3.Headers
import retrofit2.Response
import java.io.IOException

sealed class ApiResult<out T> {
    class Success<T>(val data: T, val headers: Headers? = null) : ApiResult<T>() {}

    class Error(val error: Exception) : ApiResult<Nothing>()
}

//To be used to decifer exceptions thrown in api calls
fun Throwable.getApiErrorMessage() =
    when {
        this is IOException ->
            UiText.StringResource(R.string.general_error_no_internet_connection)
        !message.isNullOrEmpty() -> UiText.DynamicString(message!!)
        else -> UiText.StringResource(R.string.general_error_unknown)
    }

fun <T, G> ApiResult<T>.mapDtoToDomain(mapper: T.() -> G): ApiResult<G> {
    return when (this) {
        is ApiResult.Error -> this
        is ApiResult.Success -> ApiResult.Success(this.data.mapper())
    }
}

//To be used in remote api calls
suspend fun <T> safeApiGenericResult(
    call: suspend () -> Response<T>,
): ApiResult<T> {
    var response: Response<T>? = null
    response = call.invoke()
    return try {
        when {
            response.isSuccessful -> ApiResult.Success(response.body()!!, response.headers())

            response.errorBody() != null -> {
                val exception = Exception(response.errorBody().toString())
                ApiResult.Error(exception)
            }
            else -> {
                val exception = Exception(response.raw().message)
                ApiResult.Error(exception)
            }
        }
    } catch (exception: Exception) {
        ApiResult.Error(exception)
    }


}


//To be used in remote api calls
suspend fun <T, G : BaseResponse<T>> safeApiBaseResult(
    call: suspend () -> Response<G>,
): ApiResult<T> {
    var response: Response<G>? = null
    return try {
        response = call.invoke()
        when {
            response.code() == 401 -> {

                ApiResult.Error(Exception("Unauthorized"))
            }
            response.isSuccessful -> {
                when {
                    response.body()?.status != "success" ->
                        ApiResult.Error(Exception((response.body() as BaseResponse<*>).getErrorMessages()))
                    //Generic error needs fixing from backend
                    response.body()?.body == null ->
                        ApiResult.Error(Exception())
                    else ->
                        ApiResult.Success(response.body()!!.body!!, response.headers())
                }
            }

            response.errorBody() != null -> {
                val exception = try {
                    val body = response.errorBody()!!.charStream().readText()
                    val gson = Gson()
                    val type = object : TypeToken<BaseError>() {}.type
                    val errorBody: BaseError? =
                        gson.fromJson(body, type)

                    Exception(errorBody?.getMessages() ?: body)
                } catch (e: Exception) {
                    Exception(response.errorBody().toString())
                }
                ApiResult.Error(exception)
            }
            else -> {
                val exception = Exception(response.raw().message)
                ApiResult.Error(exception)
            }
        }
    } catch (exception: Exception) {
        ApiResult.Error(exception)
    }


}


