package com.klid.gads.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.klid.gads.R
import com.klid.gads.adapter.LearnerAdapter
import com.klid.gads.databinding.FragmentLearningLeaderBinding

class LearningLeaderFragment : Fragment() {

    private val viewModel: LearningLeaderViewModel by lazy {
        return@lazy LearningLeaderViewModel()
    }

    private lateinit var binding: FragmentLearningLeaderBinding
    private lateinit var learnerAdapter: LearnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_learning_leader, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        learnerAdapter = LearnerAdapter()
        binding.itemRecyclerView.adapter = learnerAdapter

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.learners.observe(viewLifecycleOwner, Observer {
            learnerAdapter.submitList(it)
        })
    }

}