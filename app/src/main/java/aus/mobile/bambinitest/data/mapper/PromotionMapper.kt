package aus.mobile.bambinitest.data.mapper

import aus.mobile.bambinitest.data.datasource.database.entity.PromotionEntity
import aus.mobile.bambinitest.data.datasource.rest.model.Proline
import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion

internal fun PromotionEntity.entityToPromotion(): Promotion =
    Promotion(
        title = title,
        backgroundColor = backgroundColor,
        duration = duration,
        periodicity = periodicity,
        textColor = textColor
    )

internal fun Proline.responseToEntity(): PromotionEntity =
    PromotionEntity(
        title = content,
        backgroundColor = highlight?.backgroundColor ?: "",
        duration = duration,
        periodicity = highlight?.periodicity ?: 0,
        textColor = highlight?.textColor ?: ""
    )