package com.hsmnzaydn.waaperhd.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.ui.FragmentView.Companion.currentController

class FragmentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    lateinit var activity: Activity
    lateinit var appCompatActivity: AppCompatActivity
    lateinit var supportFragmentManager: FragmentManager


    init {
        LayoutInflater.from(context)
            .inflate(R.layout.fragment_view, this, true)
    }

    fun bind(activity: Activity) {
        this.activity = activity
        this.appCompatActivity = activity as AppCompatActivity
        this.supportFragmentManager = appCompatActivity.supportFragmentManager
        controller = this

        onBackPress()
    }

    private fun onBackPress() {
        appCompatActivity.onBackPressedDispatcher.addCallback(appCompatActivity, onBackListener)
    }

    private val onBackListener = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navigateUp()
        }
    }

    fun navigate(fragment: Fragment, popup: Fragment? = null) {
        transaction(fragment, popup, supportFragmentManager)
    }

    fun childNavigate(fragment: Fragment, popup: Fragment? = null) {
        transaction(fragment, popup, currentFragment()?.childFragmentManager)
    }

    private fun transaction(
        fragment: Fragment,
        popup: Fragment?,
        fragmentManager: FragmentManager?
    ) {
        val name = fragment.javaClass.name
        val stackFragments = stackFragmentList(fragmentManager)
        val fragmentName = stackFragments.find { it == name }

        fragmentManager?.let {
            if (fragmentName != null) {
                findFragmentByTag(fragmentName, fragmentManager)?.let {
                    fragmentManager.beginTransaction().remove(it).commit()
                    popStackInclusive(fragmentManager, fragmentName)
                }
            }
            popup?.let {
                val popupFragmentName = popup.javaClass.name
                var bundle = fragment.arguments
                if (bundle == null) bundle = Bundle()
                bundle.putString(POPUP_FRAGMENT, popupFragmentName)
                fragment.arguments = bundle
            }
            fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, name)
                .addToBackStack(name).commit()
        }

    }

    fun navigateUp() {
        currentFragment()?.arguments?.let {
            val popupFragmentTag = it.getString(POPUP_FRAGMENT)
            popupFragmentTag?.let { fragmentTag -> backtoSpesificFragment(fragmentTag) }
                ?: popStack()

        } ?: popStack()
    }

    private fun backtoSpesificFragment(fragmentTag: String) {
        if (checkStackFragment(fragmentTag)) {
            popStackInclusive(supportFragmentManager, fragmentTag)
        } else {
            popStack()
        }
    }


    private fun popStack() {
        val childFragmentManager = currentFragment(supportFragmentManager)?.childFragmentManager

        childFragmentManager?.let {
            if (it.backStackEntryCount > 1) {
                it.popBackStack()
            } else {
                supportFragmentBackStack()
            }
        } ?: supportFragmentBackStack()
    }

    private fun popStackInclusive(fragmentManager: FragmentManager, tag: String) {
        fragmentManager.popBackStack(
            tag,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun supportFragmentBackStack() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }


    private fun checkStackFragment(fragmentTag: String): Boolean {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            if (fragmentTag == supportFragmentManager.getBackStackEntryAt(i).name) {
                return true
            }
        }
        return false
    }

    private fun stackFragmentList(fragmentManager: FragmentManager?): List<String> {
        fragmentManager?.let {
            val fragments = ArrayList<String>()

            for (i in 0 until fragmentManager.backStackEntryCount) {
                val name = fragmentManager.getBackStackEntryAt(i).name
                fragments.add(name!!)
            }
            return fragments
        }

        return emptyList()
    }


    companion object {
        const val POPUP_FRAGMENT = "popup_fragment"
        lateinit var controller: FragmentView

        fun currentController() = controller
    }

}

val Fragment.controller: FragmentView get() = currentController()

fun FragmentView.currentFragment(fragmentManager: FragmentManager = supportFragmentManager) =
    fragmentManager.findFragmentById(R.id.container)

fun FragmentView.findFragmentByTag(tag: String, fragmentManager: FragmentManager) =
    fragmentManager.findFragmentByTag(tag)

fun FragmentView.findFragmentById(id: Int, fragmentManager: FragmentManager) =
    fragmentManager.findFragmentById(id)