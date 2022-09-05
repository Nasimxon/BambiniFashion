package aus.mobile.bambinitest.domain.data.repository.promotion

import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion
import kotlinx.coroutines.flow.Flow

interface PromotionRepository {

    fun getPromotions(): Flow<List<Promotion>>

}