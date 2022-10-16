package com.bonbon.library.textchipviews

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.R
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bonbon.library.corecomponent.CoreChipView
import com.bonbon.library.model.FilterableEntity

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun OutLinedTextChipView(
    modifier: Modifier = Modifier,
    textPadding: Dp = 8.dp,
    chipItems: MutableList<String?>,
    text: String,
    shape: Shape = MaterialTheme.shapes.medium,
    focusColor: Color = MaterialTheme.colors.primary,
    unFocusColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    cursorBrush: Brush = SolidColor(Color.Black),
    show: () -> Unit = {},
    icon: Painter?,
    enable: Boolean = true,
    onValueChange: (String) -> Unit,
    onKeyEvent: (KeyEvent) -> Unit,
    chipContent: @Composable (String) -> Unit,
    dropDownContent: @Composable (String) -> Unit,
) {

    var isFocused by remember {
        mutableStateOf(false)
    }

    val focusRequester = FocusRequester()

    CoreChipView(
        modifier = modifier.border(
            border = BorderStroke(if (isFocused) 2.dp else 1.5.dp, if (isFocused) focusColor else unFocusColor),
            shape = shape
        ),
        textPadding = textPadding,
        chipItems = chipItems,
        isFocused = isFocused,
        shape = shape,
        chipContent = chipContent,
        dropDownContent = dropDownContent,
        focusRequester = focusRequester,
        show = show,
        icon = icon,
        onClicked = {
            isFocused = it
        }
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                isFocused = true
                onValueChange(it)
            }, modifier = Modifier
                .width(IntrinsicSize.Min)
                .defaultMinSize(15.dp)
                .padding(6.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                }
                .onKeyEvent {
                    onKeyEvent(it)
                    false
                }
                .focusRequester(focusRequester = focusRequester),
            cursorBrush = cursorBrush,
            textStyle = textStyle,
            enabled = enable
        )
    }
}