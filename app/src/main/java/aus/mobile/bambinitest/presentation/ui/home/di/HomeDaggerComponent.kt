package aus.mobile.bambinitest.presentation.ui.home.di

import dagger.Component
import aus.mobile.bambinitest.presentation.global.di.GlobalDaggerComponent
import aus.mobile.bambinitest.presentation.ui.home.HomeFragment

@HomeDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [HomeDaggerModule::class]
)
internal interface HomeDaggerComponent {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): HomeDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): HomeDaggerComponent =
            DaggerHomeDaggerComponent
                .factory()
                .create(component)
    }
}