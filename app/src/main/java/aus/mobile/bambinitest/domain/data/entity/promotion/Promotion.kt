package aus.mobile.bambinitest.domain.data.entity.promotion

data class Promotion(
    val title: String,
    val duration: Long,
    var periodicity: Long = 0,
    val textColor: String?,
    val backgroundColor: String?
)

fun Promotion.shouldPromote() = periodicity != 0L