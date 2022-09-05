package aus.mobile.bambinitest.presentation.support.customview

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.text.parseAsHtml
import aus.mobile.bambinitest.R
import aus.mobile.bambinitest.data.datasource.rest.model.Proline
import aus.mobile.bambinitest.domain.data.entity.promotion.Promotion
import aus.mobile.bambinitest.domain.data.entity.promotion.shouldPromote
import java.util.TimerTask

class PromotionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var tvTitle: TextView
    private var current: Int = 0

    init {
        visibility = GONE
        tvTitle = TextView(context)
        val padding = resources.getDimension(R.dimen._13sdp).toInt()
        tvTitle.setPadding(padding, padding, padding, padding)
        tvTitle.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, CENTER)
        addView(tvTitle)
    }

    var promotions: List<Promotion> = emptyList()
        set(value) {
            field = value
            current = 0
            visibility = if (promotions.isEmpty()) GONE else VISIBLE
            checkPromotions()
        }

    private fun checkPromotions() {
        if (current == promotions.size) current = 0

        if(hasActivePromotions()) {
            visibility = GONE
            return
        }

        val item = promotions[current]

        with(item) {
            if (shouldPromote().not()) checkPromotions()
            showPromotion(item)

            Handler(Looper.getMainLooper()).postDelayed({
                periodicity--
                current++
                checkPromotions()
            }, duration)
        }
    }

    private fun showPromotion(item: Promotion) {
        with(item) {
            tvTitle.text = title.parseAsHtml()

            if (textColor.isNullOrBlank().not())
                tvTitle.setTextColor(Color.parseColor(textColor))

            if (backgroundColor.isNullOrBlank().not())
                setBackgroundColor(Color.parseColor(backgroundColor))
        }
    }

    private fun hasActivePromotions() = promotions.none { it.periodicity != 0L }
}