package com.bodiart.instagram4a.refactor.kotlin.utils

import java.util.*

class InstagramGenericUtil {
    companion object{

        fun generateUuid(dash: Boolean): String {

            val uuid = UUID.randomUUID().toString()

            return if (dash) {
                uuid
            } else uuid.replace("-".toRegex(), "")

        }

        fun generateQueryParams(params: Map<String, String>): String {

            val parameters = ArrayList<String>()

            for (key in params.keys) {
                parameters.add(key + "=" + params[key])
            }

            if (parameters.size < 2) {
                return parameters[0]
            } else {
                var finalResult = ""
                for (q in parameters) {
                    finalResult += "$q&"
                }
                return finalResult.substring(0, finalResult.length - 2)
            }

        }

    }
}