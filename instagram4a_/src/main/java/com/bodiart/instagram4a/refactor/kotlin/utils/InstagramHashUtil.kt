package com.bodiart.instagram4a.refactor.kotlin.utils

import org.apache.commons.codec.binary.Hex
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.Charset
import java.security.Key
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.min

class InstagramHashUtil {

    companion object {

        private const val XLATE = "0123456789abcdef"

        private fun digest(codec: String, source: String): String {
            try {
                val digest = MessageDigest.getInstance(codec)
                val digestBytes = digest.digest(source.toByteArray(Charset.forName("UTF-8")))
                return hexlate(digestBytes, digestBytes.size)
            } catch (nsae: NoSuchAlgorithmException) {
                throw RuntimeException("$codec codec not available")
            }

        }

        private fun md5hex(source: String): String {
            return digest("MD5", source)
        }

        private fun hexlate(bytes: ByteArray?, initialCount: Int): String {
            if (bytes == null) {
                return ""
            }

            val count = min(initialCount, bytes.size)
            val chars = CharArray(count * 2)

            for (i in 0 until count) {
                var `val` = bytes[i].toInt()
                if (`val` < 0) {
                    `val` += 256
                }
                chars[2 * i] = XLATE[`val` / 16]
                chars[2 * i + 1] = XLATE[`val` % 16]
            }

            return String(chars)
        }

        fun generateDeviceId(username: String, password: String): String {
            val seed = md5hex(username + password)
            val volatileSeed = "12345"

            return "android-" + md5hex(seed + volatileSeed).substring(0, 16)
        }

        fun generateHash(key: String, string: String): String? {
            val `object` = SecretKeySpec(key.toByteArray(), "HmacSHA256")
            try {
                val mac = Mac.getInstance("HmacSHA256")
                mac.init(`object` as Key)
                val byteArray = mac.doFinal(string.toByteArray(charset("UTF-8")))
                return String(Hex().encode(byteArray), Charset.forName("ISO-8859-1")) // todo check error
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        @Throws(UnsupportedEncodingException::class)
        fun generateSignature(payload: String): String {
            val parsedData = URLEncoder.encode(payload, "UTF-8")

            val signedBody = generateHash(InstagramConstants.API_KEY, payload)

            return ("ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'.toString()
                    + parsedData)

        }

        fun mapToString(map: Map<String, String>, separator: String): String {

            val mapAsString = StringBuilder("{")
            for (key in map.keys) {
                mapAsString.append("\"").append(key).append("\"").append(":").append("[\"").append(map[key]).append("\"]").append(separator)
            }
            mapAsString.deleteCharAt(mapAsString.length - 1).append("}")
            return mapAsString.toString()
        }
    }
}