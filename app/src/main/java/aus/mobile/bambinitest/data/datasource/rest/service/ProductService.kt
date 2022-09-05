package aus.mobile.bambinitest.data.datasource.rest.service

import aus.mobile.bambinitest.data.datasource.rest.model.ProductsResponse
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

internal interface ProductService {

    @GET(PRODUCTS_PATH)
    fun getProducts(): Flow<ProductsResponse>

    companion object {
        const val PRODUCTS_PATH = "./page:type=landing"
    }
}