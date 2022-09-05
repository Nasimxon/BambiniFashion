package aus.mobile.bambinitest.presentation.support.navigator

import androidx.navigation.fragment.NavHostFragment

class NavControllerHolder<Controller: NavControllerDelegate>(val controller: Controller) {

    fun attachNavFragment(fragment: NavHostFragment) =
        controller.attachNavFragment(fragment)

    fun detachNavController() =
        controller.detach()
}