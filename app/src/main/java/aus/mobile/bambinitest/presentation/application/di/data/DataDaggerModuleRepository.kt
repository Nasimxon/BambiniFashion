package aus.mobile.bambinitest.presentation.application.di.data

import aus.mobile.bambinitest.data.repository.product.ProductRepositoryImpl
import aus.mobile.bambinitest.data.repository.promotion.PromotionRepositoryImpl
import aus.mobile.bambinitest.domain.data.repository.product.ProductRepository
import aus.mobile.bambinitest.domain.data.repository.promotion.PromotionRepository
import dagger.Binds
import dagger.Module

@Module
internal interface DataDaggerModuleRepository {

    @Binds
    fun productRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    fun promotionRepository(
        impl: PromotionRepositoryImpl
    ): PromotionRepository

}