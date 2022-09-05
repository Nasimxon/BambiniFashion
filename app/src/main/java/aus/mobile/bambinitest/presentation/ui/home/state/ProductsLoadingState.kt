package aus.mobile.bambinitest.presentation.ui.home.state

import aus.mobile.bambinitest.domain.data.entity.product.Product

sealed class ProductsLoadingState {

    object Loading : ProductsLoadingState()

    object Empty : ProductsLoadingState()

    data class Success(val list: List<Product>) : ProductsLoadingState()

    data class Failure(val throwable: Throwable) : ProductsLoadingState()
}