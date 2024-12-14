package com.nature_farm.android.homepage.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nature_farm.android.homepage.ui.main.order.OrderCancelFragment
import com.nature_farm.android.homepage.ui.main.order.OrderCompleteFragment
import com.nature_farm.android.homepage.ui.main.order.OrderPendingFragment

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