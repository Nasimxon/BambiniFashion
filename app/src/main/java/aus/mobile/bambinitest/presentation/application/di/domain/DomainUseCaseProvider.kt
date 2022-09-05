package aus.mobile.bambinitest.presentation.application.di.domain

import aus.mobile.bambinitest.domain.usecase.product.ProductUseCase
import aus.mobile.bambinitest.domain.usecase.promotion.PromotionUseCase

interface DomainUseCaseProvider {
    val productUseCase: ProductUseCase
    val promotionUseCase: PromotionUseCase
}