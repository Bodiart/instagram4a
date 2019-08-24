package com.bodiart.instagram4a.refactor.kotlin.request.base

import com.bodiart.instagram4a.exceptions.RequestErrorException
import com.bodiart.instagram4a.exceptions.ServerErrorException
import com.bodiart.instagram4a.payload.base.StatusResult
import com.bodiart.instagram4a.refactor.kotlin.Instagram4Android
import com.bodiart.instagram4a.refactor.kotlin.utils.InstagramConstants
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

abstract class InstagramRequest <T> {

    protected lateinit var api : Instagram4Android

    /**
     * @return the url
     */
    abstract fun getUrl(): String

    /**
    * is api request requires api v2 url
    * */
    fun requireApiV2() = false

    /**
     * @return the method
     */
    abstract fun getMethod(): String

    /**
     * @return the payload
     */
    fun getPayload(): String? = null

    /**
     * @return the result
     */
    abstract fun execute(): T


    /**
     * Process response
     * @param resultCode Status Code
     * @param content Content
     */
    abstract fun parseResult(resultCode: Int, content: String?): T

    /**
     * @return if request must be logged in
     */
    fun requiresLogin() = true

    /**
     * Parses Json into type, considering the status code
     * @param statusCode HTTP Status Code
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    @Throws(RequestErrorException::class, ServerErrorException::class)
    fun <U> parseJson(statusCode: Int, str: String, clazz: Class<U>): U{

        InstagramConstants.log(tag, str)

        if (clazz.isAssignableFrom(StatusResult::class.java)){
            when(statusCode){
                404, 403 -> {
                    throw RequestErrorException("Request error")
                }
                500, 501, 502, 503, 504, 505 -> {
                    throw ServerErrorException("Server error")
                }
            }
        }

        return parseJson(str, clazz)
    }

    /**
     * Parses Json into type
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    fun <U> parseJson(str: String, clazz: Class<U>): U{
        return ObjectMapper()
                .configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false)
                .readValue(str, clazz)
    }




    companion object{
        const val tag = "InstagramRequest"
    }
}