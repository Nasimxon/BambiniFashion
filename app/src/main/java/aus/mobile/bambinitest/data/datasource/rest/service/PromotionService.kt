package aus.mobile.bambinitest.data.datasource.rest.service

import aus.mobile.bambinitest.data.datasource.rest.model.ProlineResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

internal interface PromotionService {

    @GET(PROLINE_PATH)
    fun getPromotions(): Flow<ProlineResponse>

    companion object {
        const val PROLINE_PATH = "./user:proline"
    }
}