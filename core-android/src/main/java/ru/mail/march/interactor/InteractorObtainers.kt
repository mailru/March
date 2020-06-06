package ru.mail.march.interactor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner

class InteractorObtainers {

    companion object {
        @JvmStatic
        fun from(activity: FragmentActivity): InteractorObtainer {
            return attach(activity, activity)
        }

        @JvmStatic
        fun from(fragment: Fragment): InteractorObtainer {
            return attach(fragment, fragment)
        }

        private fun attach(
            viewModelStoreOwner: ViewModelStoreOwner,
            lifecycleOwner: LifecycleOwner
        ): InteractorObtainer {
            return ViewModelInteractorObtainer(
                viewModelStoreOwner.viewModelStore,
                lifecycleOwner
            )
        }
    }
}
