package aus.mobile.bambinitest.data.datasource.database.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import aus.mobile.bambinitest.data.datasource.database.dao.ProductEntityDao
import aus.mobile.bambinitest.data.datasource.database.dao.PromotionEntityDao
import aus.mobile.bambinitest.data.datasource.database.entity.ProductEntity
import aus.mobile.bambinitest.data.datasource.database.entity.PromotionEntity

@Database(
    entities = [
        ProductEntity::class, PromotionEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val productEntityDao: ProductEntityDao

    abstract val promotionEntityDao: PromotionEntityDao

    companion object {

        private const val DATABASE_NAME: String = "bambini_database"

        fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addMigrations(*AppDatabaseMigrations.migrations)
                .build()
    }
}