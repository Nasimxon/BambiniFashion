package aus.mobile.bambinitest.presentation.global.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import aus.mobile.bambinitest.presentation.global.GlobalViewModel
import aus.mobile.bambinitest.presentation.support.viewmodel.ViewModelFactory
import aus.mobile.bambinitest.presentation.support.viewmodel.ViewModelKey

@Module(includes = [GlobalDaggerModule.Binder::class])
object GlobalDaggerModule {

    @Module
    interface Binder {

        @Binds
        @GlobalScope
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @GlobalScope
        @ViewModelKey(GlobalViewModel::class)
        fun viewModel(viewModel: GlobalViewModel): ViewModel

    }
}