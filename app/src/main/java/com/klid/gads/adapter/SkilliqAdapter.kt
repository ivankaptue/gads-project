package com.klid.gads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.klid.gads.R
import com.klid.gads.api.model.Skilliq
import com.klid.gads.databinding.ItemLayoutBinding

/**
 * @author Ivan Kaptue (ivanokaptue@gmail.com)
 */
class SkilliqAdapter : ListAdapter<Skilliq, SkilliqAdapter.SkilliqViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Skilliq>() {
        override fun areItemsTheSame(oldItem: Skilliq, newItem: Skilliq): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Skilliq, newItem: Skilliq): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class SkilliqViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skilliq: Skilliq) {
            binding.nameTextview.text = skilliq.name
            binding.descriptionTextview.text =
                "${skilliq.score} skill IQ Score, ${skilliq.country}"

            Glide.with(binding.root.context)
                .load(skilliq.badgeUrl)
                .into(binding.iconImageview)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkilliqViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return SkilliqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkilliqViewHolder, position: Int) {
        val learner = getItem(position)
        learner?.let {
            holder.bind(it)
        }
    }

}