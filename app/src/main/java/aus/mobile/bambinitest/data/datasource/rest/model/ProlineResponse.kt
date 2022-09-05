package aus.mobile.bambinitest.data.datasource.rest.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProlineResponse(
    @SerialName("user")
    val user: ProlineUser
)

@Serializable
data class ProlineUser(
    @SerialName("proline")
    val prolinePosition: ProlinePositionWrapper
)

@Serializable
data class ProlinePositionWrapper(
    @SerialName("center")
    val center: ProlinePosition? = null
)

@Serializable
data class ProlinePosition(
    @SerialName("items")
    val items: List<Proline>
)

@Serializable
data class Proline(
    @SerialName("duration")
    val duration: Long,
    @SerialName("content")
    val content: String,
    @SerialName("highlight")
    val highlight: Highlight? = null
)

@Serializable
data class Highlight(
    @SerialName("periodicity")
    val periodicity: Long? = null,
    @SerialName("backgroundColor")
    val backgroundColor: String? = null,
    @SerialName("textColor")
    val textColor: String? = null
)