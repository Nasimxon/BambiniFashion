package aus.mobile.bambinitest.presentation.application.di.domain

import aus.mobile.bambinitest.domain.usecase.product.ProductUseCase
import aus.mobile.bambinitest.domain.usecase.product.ProductUseCaseImpl
import aus.mobile.bambinitest.domain.usecase.promotion.PromotionUseCase
import aus.mobile.bambinitest.domain.usecase.promotion.PromotionUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DomainDaggerModuleUseCase.Binders::class
    ]
)
internal object DomainDaggerModuleUseCase {

    @Module
    interface Binders {

        @Binds
        fun bindProductsUseCase(
            impl: ProductUseCaseImpl
        ): ProductUseCase

        @Binds
        fun bindPromotionUseCase(
            impl: PromotionUseCaseImpl
        ): PromotionUseCase

    }
}