package aus.mobile.bambinitest.domain.usecase.promotion

import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion
import kotlinx.coroutines.flow.Flow

interface PromotionUseCase {

    fun getPromotions(): Flow<List<Promotion>>

}