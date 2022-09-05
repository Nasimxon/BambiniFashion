package aus.mobile.bambinitest.data.datasource.rest.interceptor

import aus.mobile.bambinitest.presentation.support.connectivity.Connectivity
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    private val connectivity: Connectivity
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivity.hasNetworkAccess())
            throw ConnectException("Check your internet connection")
        else return chain.proceed(
            chain.request().newBuilder()
                .addHeader("bf-api-key", "oMXmKN4YSgD8RgeFfMOF54FdyENIxp")
                .addHeader("bf-localization", "AT,USD,en")
                .build()
        )
    }
}