package aus.mobile.bambinitest.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import aus.mobile.bambinitest.R
import aus.mobile.bambinitest.databinding.FragmentHomeBinding
import aus.mobile.bambinitest.presentation.global.GlobalActivity
import aus.mobile.bambinitest.presentation.support.navigator.GlobalNavController
import aus.mobile.bambinitest.presentation.ui.home.di.HomeDaggerComponent
import aus.mobile.bambinitest.presentation.ui.home.state.ProductsLoadingState
import aus.mobile.bambinitest.presentation.ui.home.state.PromotionsLoadingState
import javax.inject.Inject

internal class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var globalNavController: GlobalNavController

    private lateinit var binding: FragmentHomeBinding

    private val adapter: ProductsAdapter by lazy { ProductsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        HomeDaggerComponent
            .create((requireActivity() as GlobalActivity).globalDaggerComponent)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        with(binding) {
            recyclerView.adapter = adapter
            val layoutManager = GridLayoutManager(context, 2)
                .apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (adapter.currentList[position].isLarge) 2 else 1
                        }
                    }
                }
            recyclerView.layoutManager = layoutManager
        }
        if (savedInstanceState == null) {
            viewModel.loadProducts()
            viewModel.loadPromotions()
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.productMutableLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ProductsLoadingState.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    adapter.submitList(state.list)
                }
                is ProductsLoadingState.Empty -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.tvEmpty.visibility = View.VISIBLE
                }
                is ProductsLoadingState.Failure -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
                is ProductsLoadingState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        }
        viewModel.promotionMutableLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is PromotionsLoadingState.Success -> {
                    binding.promotionView.promotions = state.list
                }
                is PromotionsLoadingState.Empty -> {
                    binding.promotionView.promotions = emptyList()
                }
                is PromotionsLoadingState.Failure -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
                is PromotionsLoadingState.Loading -> {
                    //TODO
                }
            }
        }
    }
}