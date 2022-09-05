package aus.mobile.bambinitest.presentation.ui.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import aus.mobile.bambinitest.presentation.ui.home.HomeViewModel
import aus.mobile.bambinitest.presentation.support.viewmodel.ViewModelFactory
import aus.mobile.bambinitest.presentation.support.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HomeDaggerModule.Binder::class])
internal object HomeDaggerModule {

    @Module
    interface Binder {

        @Binds
        @HomeDaggerScope
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @HomeDaggerScope
        @ViewModelKey(HomeViewModel::class)
        fun viewModel(viewModel: HomeViewModel): ViewModel

    }
}