package aus.mobile.bambinitest.data.datasource.rest.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("page")
    val page: LandingWrapper
)

@Serializable
data class LandingWrapper(
    @SerialName("content")
    val content: List<Content>
)

@Serializable
data class Content(
    @SerialName("name")
    val name: String,
    @SerialName("data")
    val data: LandingData
)

@Serializable
data class LandingData(
    @SerialName("size")
    val size: String = "",
    @SerialName("image")
    val image: ImageSrc? = null,
    @SerialName("title")
    val title: String? = "",
    @SerialName("isMainImageRight")
    val isMainImageRight: Boolean? = false,
    @SerialName("categories")
    val categories: List<Category>? = null,
    @SerialName("caption")
    val caption: Caption? = null
)

@Serializable
data class Category(
    @SerialName("title")
    val title: String? = null,
    @SerialName("linkUrl")
    val linkUrl: String? = null,
    @SerialName("image")
    val image: ImageSrc? = null,
    @SerialName("backgroundColor")
    val backgroundColor: String? = null
)

@Serializable
data class Caption(
    @SerialName("heading")
    val heading: Heading? = null
)

@Serializable
data class Heading(
    @SerialName("text")
    val text: String,
    @SerialName("isHidden")
    val isHidden: Boolean
)

@Serializable
data class ImageSrc(
    @SerialName("src")
    val src: String
)