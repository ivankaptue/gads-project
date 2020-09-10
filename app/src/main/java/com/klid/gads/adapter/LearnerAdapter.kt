package com.klid.gads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.klid.gads.R
import com.klid.gads.api.model.Learner
import com.klid.gads.databinding.ItemLayoutBinding

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class LearnerAdapter : ListAdapter<Learner, LearnerAdapter.LearnerViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Learner>() {
        override fun areItemsTheSame(oldItem: Learner, newItem: Learner): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Learner, newItem: Learner): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class LearnerViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(learner: Learner) {
            binding.nameTextview.text = learner.name
            binding.descriptionTextview.text =
                "${learner.hours} learning hours, ${learner.country}."

            Glide.with(binding.root.context)
                .load(learner.badgeUrl)
                .into(binding.iconImageview)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return LearnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        val learner = getItem(position)
        learner?.let {
            holder.bind(it)
        }
    }

}