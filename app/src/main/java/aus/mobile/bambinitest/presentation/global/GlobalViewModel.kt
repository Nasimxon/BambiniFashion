package aus.mobile.bambinitest.presentation.global

import androidx.lifecycle.ViewModel
import aus.mobile.bambinitest.presentation.support.navigator.GlobalNavController
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    private val globalNavController: GlobalNavController
) : ViewModel()