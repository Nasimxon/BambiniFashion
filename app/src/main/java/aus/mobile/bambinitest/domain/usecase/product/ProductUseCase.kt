package aus.mobile.bambinitest.domain.usecase.product

import aus.mobile.bambinitest.domain.data.entity.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {

    fun getProducts(): Flow<List<Product>>

}