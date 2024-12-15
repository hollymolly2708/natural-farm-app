package com.natural_farm.android.e_commerce.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.natural_farm.android.e_commerce.ui.main.order.OrderCancelFragment
import com.natural_farm.android.e_commerce.ui.main.order.OrderCompleteFragment
import com.natural_farm.android.e_commerce.ui.main.order.OrderPendingFragment

class OrderTabLayoutAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = OrderPendingFragment()
            1 -> fragment = OrderCancelFragment()
            2 -> fragment = OrderCompleteFragment()
        }
        return fragment as Fragment
    }
}