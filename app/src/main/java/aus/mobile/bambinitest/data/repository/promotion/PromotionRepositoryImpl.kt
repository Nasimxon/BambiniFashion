package aus.mobile.bambinitest.data.repository.promotion

import aus.mobile.bambinitest.data.datasource.database.dao.PromotionEntityDao
import aus.mobile.bambinitest.data.datasource.rest.service.PromotionService
import aus.mobile.bambinitest.data.mapper.entityToPromotion
import aus.mobile.bambinitest.data.mapper.responseToEntity
import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion
import aus.mobile.bambinitest.domain.data.repository.promotion.PromotionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

internal class PromotionRepositoryImpl @Inject constructor(
    private val promotionService: PromotionService,
    private val promotionEntityDao: PromotionEntityDao
) : PromotionRepository {

    override fun getPromotions(): Flow<List<Promotion>> {
        return promotionService.getPromotions()
            .map {
                it.user.prolinePosition.center?.items ?: listOf()
            }
            .catch {
                if (it is ConnectException || it is UnknownHostException) emit(mutableListOf())
                else throw it
            }.onEach { list ->
                if (list.isNotEmpty()) {
                    promotionEntityDao.delete()
                    list.forEach {
                        promotionEntityDao.insert(it.responseToEntity())
                    }
                }
            }
            .flatMapConcat {
                promotionEntityDao.getPromotionEntities()
                    .map { list ->
                        list.map {
                            it.entityToPromotion()
                        }
                    }
            }
    }
}