package com.nature_farm.android.homepage.utils

import android.app.Activity
import android.content.Context

object SharedPrefHelper {

    fun saveSelectedSort(activity: Activity, sort: String?) {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("selectedSort", sort).apply()
    }

    fun saveSelectedCategory(activity: Activity, category: String?) {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)


        sharedPreferences.edit().putString("selectedCategory", category).apply()


    }

    fun saveSelectedLimit(activity: Activity, limit: Int) {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("selectedLimit", limit).apply()
    }

    fun getSelectedSort(activity: Activity): String? {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selectedSort", null)
    }

    fun getSavedLimit(activity: Activity): Int {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("selectedLimit", 0)
    }

    fun getSavedCategory(activity: Activity): String? {
        val sharedPreferences =
            activity.getSharedPreferences("FilterPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selectedCategory", null)
    }

}