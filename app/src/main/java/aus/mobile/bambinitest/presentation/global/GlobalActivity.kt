package aus.mobile.bambinitest.presentation.global

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import aus.mobile.bambinitest.R
import aus.mobile.bambinitest.databinding.ActivityGlobalBinding
import aus.mobile.bambinitest.presentation.application.Application
import aus.mobile.bambinitest.presentation.global.di.GlobalDaggerComponent
import aus.mobile.bambinitest.presentation.support.navigator.KeepStateNavigator
import javax.inject.Inject

internal class GlobalActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GlobalViewModel by viewModels { viewModelFactory }

    private val binding by lazy { ActivityGlobalBinding.inflate(layoutInflater) }

    lateinit var globalDaggerComponent: GlobalDaggerComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        GlobalDaggerComponent
            .create((application as Application).applicationDaggerComponent)
            .also { globalDaggerComponent = it }
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navHostFragment?.let {
            findNavController(R.id.nav_host_fragment)
                .apply {
                    navigatorProvider += KeepStateNavigator(this@GlobalActivity,
                        it.childFragmentManager, R.id.nav_host_fragment)
                    setGraph(R.navigation.nav_graph)
                    binding.bottomNavigationView.setupWithNavController(this)
                }
        }
    }
}