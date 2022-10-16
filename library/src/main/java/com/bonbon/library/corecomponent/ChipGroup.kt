package com.bonbon.library.corecomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ChipGroup(
    items: List<String>,
    modifier: Modifier = Modifier,
    content: @Composable (String) -> Unit
) {
    items.forEach {
        Box(modifier = modifier.padding(2.dp)) {
            content(it)
        }
    }

}