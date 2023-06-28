package com.danielwaiguru.radius.models

data class Facility(
    val facilityId: String,
    val name: String,
    val options: List<SelectableOption>
) {
    data class SelectableOption(
        val icon: String,
        val id: String,
        val name: String,
        val isSelectable: Boolean = true,
        val isSelected: Boolean = false
    )
}