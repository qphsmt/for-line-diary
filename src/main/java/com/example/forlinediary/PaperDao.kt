package com.example.forlinediary

import java.io.BufferedInputStream
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun httpDonwload(url: String, accessToken: String, apiArgs: String): InputStream? {

    var con = URL(url).openConnection() as HttpsURLConnection

    con.apply {
        requestMethod = "POST"
        connectTimeout = 3000
        readTimeout = 5000
        instanceFollowRedirects = true
        setRequestProperty("Authorization", accessToken)
        setRequestProperty("Dropbox-API-Arg", apiArgs)
    }

    con.connect()

    if (con.responseCode in 200..299) {
        return BufferedInputStream(con.inputStream)
    }

    return null
}
