package aus.mobile.bambinitest.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import aus.mobile.bambinitest.data.datasource.database.entity.PromotionEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class PromotionEntityDao {

    @Query("SELECT * FROM promotions ORDER BY promotion_id")
    abstract fun getPromotionEntities(): Flow<List<PromotionEntity>>

    @Query("SELECT * FROM promotions WHERE promotion_id = :promotionId")
    abstract fun getPromotionEntityById(promotionId: Long): Flow<PromotionEntity>

    @Query("SELECT * FROM promotions WHERE promotion_id = :promotionId")
    abstract fun getPromotionEntityByIdWithoutFlow(promotionId: Long): PromotionEntity?

    @Query("DELETE FROM promotions")
    abstract fun delete()

    @Delete
    abstract fun delete(value: PromotionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(value: PromotionEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(collection: Collection<PromotionEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(value: PromotionEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(collection: Collection<PromotionEntity>)

    @Transaction
    open fun updatePromotion(promotionEntity: PromotionEntity) {
        val entity = getPromotionEntityByIdWithoutFlow(promotionEntity.promotionId)
        update(
            promotionEntity.copy(
                title = entity?.title ?: "",
                duration = entity?.duration ?: 0L,
                periodicity = entity?.periodicity ?: 0L,
                backgroundColor = entity?.backgroundColor ?: ""
            )
        )
    }
}