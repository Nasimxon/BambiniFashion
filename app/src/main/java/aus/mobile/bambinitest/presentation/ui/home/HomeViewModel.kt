package aus.mobile.bambinitest.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aus.mobile.bambinitest.domain.usecase.product.ProductUseCase
import aus.mobile.bambinitest.domain.usecase.promotion.PromotionUseCase
import aus.mobile.bambinitest.presentation.ui.home.state.ProductsLoadingState
import aus.mobile.bambinitest.presentation.ui.home.state.PromotionsLoadingState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val promotionUseCase: PromotionUseCase
) : ViewModel() {

    private val _productsMutableLiveData = MutableLiveData<ProductsLoadingState>()
    val productMutableLiveData: LiveData<ProductsLoadingState> = _productsMutableLiveData

    private val _promotionMutableLiveData = MutableLiveData<PromotionsLoadingState>()
    val promotionMutableLiveData: LiveData<PromotionsLoadingState> = _promotionMutableLiveData

    fun loadProducts() {
        productUseCase.getProducts()
            .onStart { _productsMutableLiveData.value = ProductsLoadingState.Loading }
            .catch { _productsMutableLiveData.value = ProductsLoadingState.Failure(it) }
            .onEach { list ->
                _productsMutableLiveData.value =
                    if (list.isNotEmpty()) ProductsLoadingState.Success(list)
                    else ProductsLoadingState.Empty
            }
            .launchIn(viewModelScope)
    }

    fun loadPromotions() {
        promotionUseCase.getPromotions()
            .onStart { _promotionMutableLiveData.value = PromotionsLoadingState.Loading }
            .catch { _promotionMutableLiveData.value = PromotionsLoadingState.Failure(it) }
            .onEach { list ->
                _promotionMutableLiveData.value =
                    if (list.isNotEmpty()) PromotionsLoadingState.Success(list)
                    else PromotionsLoadingState.Empty
            }
            .launchIn(viewModelScope)
    }
}