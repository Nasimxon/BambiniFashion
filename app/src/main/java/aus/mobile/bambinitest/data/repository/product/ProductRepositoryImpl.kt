package aus.mobile.bambinitest.data.repository.product

import aus.mobile.bambinitest.data.datasource.database.dao.ProductEntityDao
import aus.mobile.bambinitest.data.datasource.rest.service.ProductService
import aus.mobile.bambinitest.data.mapper.entityToProduct
import aus.mobile.bambinitest.data.mapper.responseToEntity
import aus.mobile.bambinitest.domain.data.entity.product.Product
import aus.mobile.bambinitest.domain.data.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productEntityDao: ProductEntityDao
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return productService.getProducts()
            .map { it ->
                val result = mutableListOf<Product>()
                it.page.content.forEach { content ->
                    if (content.name == "banner") {
                        result.add(0, Product(true, content.data.caption?.heading?.text, content.data.image?.src))
                    } else {
                        result.add(
                            Product(
                                true,
                                content.data.title,
                                content.data.image?.src
                            )
                        )
                        result.addAll(content.data.categories?.map {
                            Product(false, it.title, it.image?.src, backgroundColor = it.backgroundColor)
                        } as Collection<Product>)
                    }
                }
                result.filter { it.image != null }
            }
            .catch {
                if (it is ConnectException || it is UnknownHostException) emit(mutableListOf())
                else throw it
            }
            .onEach { list ->
                if (list.isNotEmpty()) {
                    productEntityDao.delete()
                    list.forEach {
                        productEntityDao.insert(it.responseToEntity())
                    }
                }
            }
            .flatMapConcat {
                productEntityDao.getProductEntities()
                    .map { list ->
                        list.map {
                            it.entityToProduct()
                        }
                    }
            }
    }
}