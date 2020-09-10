package com.klid.gads.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.klid.gads.fragment.LearningLeaderFragment
import com.klid.gads.fragment.SkillIQLeaderFragment

class ViewPagerFragmentAdapter(
    fragmentActivity: FragmentActivity,
    private val titles: List<String>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LearningLeaderFragment()
            1 -> SkillIQLeaderFragment()
            else -> throw IllegalArgumentException("No fragment found at position $position")
        }
    }

}
