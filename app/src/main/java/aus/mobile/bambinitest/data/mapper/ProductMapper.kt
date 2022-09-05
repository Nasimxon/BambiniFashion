package aus.mobile.bambinitest.data.mapper

import aus.mobile.bambinitest.data.datasource.database.entity.ProductEntity
import aus.mobile.bambinitest.domain.data.entity.product.Product

internal fun ProductEntity.entityToProduct(): Product =
    Product(
        isLarge = isLarge,
        title = title,
        image = image,
        backgroundColor = backgroundColor
    )

internal fun Product.responseToEntity(): ProductEntity =
    ProductEntity(
        isLarge = isLarge,
        title = title ?: "",
        image = image ?: "",
        backgroundColor = backgroundColor ?: ""
    )