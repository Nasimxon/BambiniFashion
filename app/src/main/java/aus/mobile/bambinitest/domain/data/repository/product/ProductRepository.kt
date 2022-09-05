package aus.mobile.bambinitest.domain.data.repository.product

import aus.mobile.bambinitest.domain.data.entity.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

}