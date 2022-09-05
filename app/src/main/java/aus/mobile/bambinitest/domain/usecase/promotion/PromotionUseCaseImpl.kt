package aus.mobile.bambinitest.domain.usecase.promotion

import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion
import aus.mobile.bambinitest.domain.data.repository.promotion.PromotionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class PromotionUseCaseImpl @Inject constructor(
    private val prolineRepository: PromotionRepository
): PromotionUseCase {

    override fun getPromotions(): Flow<List<Promotion>> {
        return prolineRepository.getPromotions()
            .flowOn(Dispatchers.IO)
    }
}