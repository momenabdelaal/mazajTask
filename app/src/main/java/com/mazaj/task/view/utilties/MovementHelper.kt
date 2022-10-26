package com.mazaj.task.view.utilties

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mazaj.task.view.main.MainActivity

object MovementHelper {
    //---------Fragments----------//
    fun popAllFragments(context: Context) {
        val fm = (context as FragmentActivity).supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }

    @JvmStatic
    fun addFragment(
        context: Context,
        fragment: Fragment?,
        activityType: Int,
        backStackText: String
    ) {
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction().add(activityType, fragment!!)
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        if (backStackText != "") {
            fragmentTransaction.addToBackStack(backStackText)
        }
        fragmentTransaction.commit()
    }

    fun replaceFragment(
        context: Context,
        fragment: Fragment?,
        activityType: Int,
        backStackText: String
    ) {
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
            .replace(activityType, fragment!!)
        if (backStackText != "") {
            fragmentTransaction.addToBackStack(backStackText)
        }
        fragmentTransaction.commit()
    }

    fun popLastFragment(context: Context) {
        (context as FragmentActivity).supportFragmentManager.popBackStack()
    }

    //-----------Activities-----------------//
    fun startActivity(context: Context, activity: Class<*>?, bundle: String?) {
        val intent = Intent(context, activity)
        intent.putExtra(Constants.INTENT_BUNDLE, bundle)
        context.startActivity(intent)
    }

    fun startActivityForResult(
        context: Context, activity: Class<*>?,
        requestCode: Int, page: Int, bundle: String?
    ) {
        val intent = Intent(context, activity)
        intent.putExtra(Constants.INTENT_PAGE, page)
        intent.putExtra(Constants.INTENT_BUNDLE, bundle)
        (context as AppCompatActivity).startActivityForResult(intent, requestCode)
    }
    fun startMainActivity(context: Context, page: Int) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(Constants.INTENT_PAGE, page)
        context.startActivity(intent)
    }


}