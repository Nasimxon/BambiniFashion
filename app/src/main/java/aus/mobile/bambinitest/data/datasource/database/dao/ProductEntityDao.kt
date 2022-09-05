package aus.mobile.bambinitest.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import aus.mobile.bambinitest.data.datasource.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class ProductEntityDao {

    @Query("SELECT * FROM products ORDER BY product_id")
    abstract fun getProductEntities(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE product_id = :productId")
    abstract fun getProductEntityById(productId: Long): Flow<ProductEntity>

    @Query("SELECT * FROM products WHERE product_id = :productId")
    abstract fun getProductEntityByIdWithoutFlow(productId: Long): ProductEntity?

    @Query("DELETE FROM products")
    abstract fun delete()

    @Delete
    abstract fun delete(value: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(value: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(collection: Collection<ProductEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(value: ProductEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(collection: Collection<ProductEntity>)

    @Transaction
    open fun updateProduct(productEntity: ProductEntity) {
        val entity = getProductEntityByIdWithoutFlow(productEntity.productId)
        update(productEntity.copy(title = entity?.title ?: "", image = entity?.image ?: ""))
    }
}