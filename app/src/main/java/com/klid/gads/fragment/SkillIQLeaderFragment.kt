package com.klid.gads.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.klid.gads.R
import com.klid.gads.adapter.SkilliqAdapter
import com.klid.gads.databinding.FragmentSkillIQLeaderBinding

class SkillIQLeaderFragment : Fragment() {

    private val viewModel: SkilliqLeaderViewModel by lazy {
        return@lazy SkilliqLeaderViewModel()
    }

    private lateinit var binding: FragmentSkillIQLeaderBinding
    private lateinit var skilliqAdapter: SkilliqAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_skill_i_q_leader, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skilliqAdapter = SkilliqAdapter()
        binding.itemRecyclerView.adapter = skilliqAdapter

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.skills.observe(viewLifecycleOwner, Observer {
            skilliqAdapter.submitList(it)
        })
    }

}