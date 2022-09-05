package aus.mobile.bambinitest.presentation.global.di

import aus.mobile.bambinitest.presentation.support.navigator.GlobalNavController
import aus.mobile.bambinitest.presentation.support.navigator.NavControllerHolder
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        GlobalDaggerModuleNavigation.Providers::class
    ]
)
object GlobalDaggerModuleNavigation {

    @Module
    object Providers {

        @JvmStatic
        @Provides
        @GlobalScope
        fun provideGlobalNavController(): GlobalNavController = GlobalNavController()

        @JvmStatic
        @Provides
        @GlobalScope
        fun provideNavControllerHolder(globalNavController: GlobalNavController)
          : NavControllerHolder<GlobalNavController> = NavControllerHolder(globalNavController)
    }

}