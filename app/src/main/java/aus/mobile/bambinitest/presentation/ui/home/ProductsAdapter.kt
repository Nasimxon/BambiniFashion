package aus.mobile.bambinitest.presentation.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import aus.mobile.bambinitest.R
import aus.mobile.bambinitest.domain.data.entity.product.Product
import com.bumptech.glide.Glide

class ProductsAdapter: ListAdapter<Product, ProductsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            if (viewType == BANNER_TYPE)
                R.layout.item_product_large
            else R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder) {
            if (item.title != null) {
                tvTitle.visibility = View.VISIBLE
                tvTitle.text = item.title
            } else tvTitle.visibility = View.GONE

            if (item.backgroundColor.isNullOrEmpty().not())
                itemParent.setBackgroundColor(Color.parseColor(item.backgroundColor))
            else itemParent.background = null

            Glide.with(ivProduct)
                .load(item.image)
                .into(ivProduct)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).isLarge) BANNER_TYPE else CATEGORY_TYPE
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val ivProduct: ImageView = view.findViewById(R.id.image_view)
        val itemParent: ViewGroup = view.findViewById(R.id.item_parent)
    }

    companion object {
        const val BANNER_TYPE = 1
        const val CATEGORY_TYPE = 2

        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

}
