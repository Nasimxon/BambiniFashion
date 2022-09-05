package aus.mobile.bambinitest.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
internal data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val productId: Long = 0,

    @ColumnInfo(name = "isLarge")
    val isLarge: Boolean,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "backgroundColor")
    val backgroundColor: String
)