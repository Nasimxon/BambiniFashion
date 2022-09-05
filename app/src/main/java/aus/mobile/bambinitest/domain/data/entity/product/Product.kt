package aus.mobile.bambinitest.domain.data.entity.product

data class Product(
    val isLarge: Boolean,
    val title: String? = null,
    val image: String? = null,
    val backgroundColor: String? = null
)