package aus.mobile.bambinitest.presentation.global.di

import dagger.Component
import aus.mobile.bambinitest.presentation.application.di.ApplicationDaggerComponent
import aus.mobile.bambinitest.presentation.application.di.domain.DomainUseCaseProvider
import aus.mobile.bambinitest.presentation.global.GlobalActivity
import aus.mobile.bambinitest.presentation.support.navigator.GlobalNavController
import aus.mobile.bambinitest.presentation.support.navigator.NavControllerHolder

@GlobalScope
@Component(
    dependencies = [ApplicationDaggerComponent::class],
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModuleNavigation::class
    ]
)
internal interface GlobalDaggerComponent : DomainUseCaseProvider {

    val globalNavController: GlobalNavController

    val navControllerHolder: NavControllerHolder<GlobalNavController>

    fun inject(activity: GlobalActivity)

    @Component.Factory
    interface Factory {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent
    }

    companion object {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent =
            DaggerGlobalDaggerComponent
                .factory()
                .create(component)
    }
}