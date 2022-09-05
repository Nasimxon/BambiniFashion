package aus.mobile.bambinitest.domain.usecase.product

import aus.mobile.bambinitest.domain.data.entity.product.Product
import aus.mobile.bambinitest.domain.data.repository.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class ProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
): ProductUseCase {

    override fun getProducts(): Flow<List<Product>> {
        return productRepository.getProducts()
            .flowOn(Dispatchers.IO)
    }
}