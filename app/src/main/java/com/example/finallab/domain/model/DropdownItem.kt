package com.example.finallab.domain.model

import androidx.annotation.DrawableRes

data class DropdownItem(
    @DrawableRes val icon: Int? = null,
    val label: String
)
