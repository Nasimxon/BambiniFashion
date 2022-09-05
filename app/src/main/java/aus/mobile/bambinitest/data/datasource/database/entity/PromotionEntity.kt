package aus.mobile.bambinitest.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotions")
internal data class PromotionEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "promotion_id")
    val promotionId: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "duration")
    val duration: Long,

    @ColumnInfo(name = "periodicity")
    val periodicity: Long,

    @ColumnInfo(name = "text_color")
    val textColor: String,

    @ColumnInfo(name = "background_color")
    val backgroundColor: String
)