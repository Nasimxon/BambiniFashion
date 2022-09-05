package aus.mobile.bambinitest.presentation.ui.home.state

import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion

sealed class PromotionsLoadingState {

    object Loading : PromotionsLoadingState()

    object Empty : PromotionsLoadingState()

    data class Success(val list: List<Promotion>) : PromotionsLoadingState()

    data class Failure(val throwable: Throwable) : PromotionsLoadingState()
}