package com.bonbon.library

import com.bonbon.library.model.FilterableEntity

data class ChipItem(
    val value: String = "",
) : FilterableEntity {
    override fun filter(query: String): Boolean {
        return value.startsWith(query, ignoreCase = true)
    }
}