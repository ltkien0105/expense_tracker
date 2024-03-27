package com.example.expense_tracker.domain.model

import androidx.annotation.DrawableRes

data class DropdownItem(
    @DrawableRes val icon: Int? = null,
    val label: String
)
