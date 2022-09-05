package aus.mobile.bambinitest.presentation.support.navigator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import java.lang.ref.WeakReference

open class NavControllerDelegate {
    private lateinit var fragmentReference: WeakReference<NavHostFragment>
    val navController: NavController? get() = fragmentReference.get()?.navController

    fun attachNavFragment(fragment: NavHostFragment) {
        fragmentReference = WeakReference(fragment)
    }

    fun detach() =
        fragmentReference.clear()

    fun popBackStack(@IdRes destinationId: Int, inclusive: Boolean): Boolean =
        navController?.popBackStack(destinationId, inclusive) ?: false

    fun popBackStack(): Boolean =
        navController?.popBackStack() ?: false

    fun navigateUp(): Boolean =
        navController?.navigateUp() ?: false

    fun handleDeepLink(intent: Intent?): Boolean =
        navController?.handleDeepLink(intent) ?: false

    fun getCurrentDestination(): NavDestination? =
        navController?.currentDestination

    fun navigate(@IdRes resId:Int) =
         navController?.navigate(resId)

     fun navigate(@IdRes resId:Int, args: Bundle?) =
        navController?.navigate(resId, args)

     fun navigate(@IdRes resId:Int, args: Bundle?, navOptions: NavOptions?) =
        navController?.navigate(resId, args, navOptions)

     fun navigate(@IdRes resId:Int, args: Bundle?, navOptions: NavOptions?, navigatorExtras: Navigator.Extras?) =
         navController?.navigate(resId, args, navOptions, navigatorExtras)

     fun navigate(deepLink: Uri, navOptions: NavOptions?) =
         navController?.navigate(deepLink, navOptions)

     fun navigate(deepLink: Uri, navOptions: NavOptions?, navigatorExtras: Navigator.Extras?) =
         navController?.navigate(deepLink, navOptions, navigatorExtras)

     fun navigate(directions: NavDirections) =
         navController?.navigate(directions)

     fun navigate(directions: NavDirections, navOptions: NavOptions?) =
         navController?.navigate(directions, navOptions)

     fun navigate(directions: NavDirections, navigatorExtras: Navigator.Extras) =
         navController?.navigate(directions, navigatorExtras)

    fun createDeepLink(): NavDeepLinkBuilder? =
        navController?.createDeepLink()

    fun saveState(): Bundle? =
        navController?.saveState()

    fun restoreState(navState: Bundle?) =
        navController?.restoreState(navState)

    fun getViewModelStoreOwner(@IdRes navGraphId: Int): ViewModelStoreOwner? =
        navController?.getViewModelStoreOwner(navGraphId)
}