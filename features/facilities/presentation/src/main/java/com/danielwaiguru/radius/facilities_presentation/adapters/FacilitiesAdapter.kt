package com.danielwaiguru.radius.facilities_presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielwaiguru.radius.facilities_presentation.databinding.ItemFacilityBinding
import com.danielwaiguru.radius.facilities_presentation.utils.drawables
import com.danielwaiguru.radius.models.Facility
import com.google.android.material.chip.Chip

class FacilitiesAdapter(
    private val onChecked: (optionId: String, isChecked: Boolean) -> Unit
): ListAdapter<Facility, FacilitiesAdapter.FacilitiesViewHolder>(
    FacilityComparator
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilitiesViewHolder {
        return FacilitiesViewHolder(
            ItemFacilityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FacilitiesViewHolder, position: Int) {
        holder.bindFacility(getItem(position), onChecked)
    }

    private object FacilityComparator: DiffUtil.ItemCallback<Facility>() {
        override fun areItemsTheSame(oldItem: Facility, newItem: Facility): Boolean =
            oldItem.facilityId == newItem.facilityId

        override fun areContentsTheSame(oldItem: Facility, newItem: Facility): Boolean =
            oldItem == newItem
    }
    class FacilitiesViewHolder(
        private val binding: ItemFacilityBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindFacility(facility: Facility, onChecked: (optionId: String, isChecked: Boolean) -> Unit) {
            with(binding) {
                val context = root.context
                txtName.text = facility.name
                facilityOptions.apply {
                    removeAllViews()
                    facility.options.forEach { option ->
                        addView(
                            Chip(context).apply {
                                setOnCheckedChangeListener(null)
                                isChecked = option.isSelected
                                isEnabled = option.isSelectable
                                text = option.name
                                id = option.id.toInt()
                                chipIcon = drawables[option.icon]?.let {
                                    ContextCompat.getDrawable(
                                        context,
                                        it
                                    )
                                }
                                setOnCheckedChangeListener { _, isChecked ->
                                    onChecked(option.id, isChecked)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}