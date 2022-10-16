package com.bonbon.library

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bonbon.library.textchipviews.OutLinedTextChipView
import com.bonbon.myapplication.ui.theme.*

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun DropDownChipsTextField(
    label: String,
    labelStyle: TextStyle = TextStyle(),
    color: Color = Neutral90,
    selectedItems: MutableList<String> = mutableStateListOf(),
    show: () -> Unit = {},
    icon: Painter?,
    value: (List<String>) -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }

    Text(
        text = label,
        style = labelStyle,
        modifier = Modifier.padding(bottom = 4.dp),
        color = color,
    )
//    val selectedItems = remember {
//        mutableStateListOf<ChipItem>()
//    }

    OutLinedTextChipView(
        modifier = Modifier,
        chipItems = selectedItems,
        shape = RoundedCornerShape(6.dp),
        text = text,
        focusColor = PrimaryBase,
        unFocusColor = Neutral40,
        onValueChange = {
            text = if (it.trim().isNotEmpty() && it.contains(TriggerSeparator.Space.value) || it.contains(TriggerSeparator.Enter.value)) {
                val trimmedText = text.trim()
                selectedItems.add(trimmedText)
                ""
            } else {
                it
            }
            value(selectedItems)
        },
        chipContent = {
            ActionChip(
                text = it,
                closeIcon = rememberVectorPainter(image = Icons.Default.Close),
                color = Neutral20,

                shape = RoundedCornerShape(18.dp)
            ) {
                selectedItems.remove(it)
            }
        },
        onKeyEvent = {
            if (it.key == Key.Backspace) {
                if (text.isEmpty() && selectedItems.count() > 0) {
                    selectedItems.removeAt(0.coerceAtLeast(selectedItems.count() - 1))
                }
            }
        },
        textStyle = TextStyle.Default.copy(color = Neutral90),
        show = show,
        icon = icon,
        enable = false
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedItems.add(it)
            }
            .padding(8.dp)) {
            Text(text = it)
        }
    }
}
