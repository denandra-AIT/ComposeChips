package com.bonbon.library.corecomponent

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bonbon.library.model.FilterableEntity
import com.bonbon.myapplication.ui.theme.PrimaryBase
import com.google.accompanist.flowlayout.FlowRow

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
internal fun <T> CoreChipView(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    textPadding: Dp = 8.dp,
//    filteredItems: List<T>,
    chipItems: MutableList<String?>,
    show: () -> Unit = {},
    icon: Painter?,
    shape: Shape = MaterialTheme.shapes.medium,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    isFocused: Boolean = false,
    onClicked: (Boolean) -> Unit,
    chipContent: @Composable (String) -> Unit,
    dropDownContent: @Composable (T) -> Unit,
    textFieldContent: @Composable () -> Unit,
) where T : FilterableEntity {

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        val boxWithConstraints = this
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(textPadding)
                    .fillMaxWidth()
                    .clickable {
                        onClicked(true)
                        keyboardController?.show()
                        focusRequester.requestFocus()

                    }, verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
                ) {
                    FlowRow(
                        modifier = Modifier.width(270.dp)
                    ) {
                        ChipGroup(
                            items = chipItems,

                        ) {
                            chipContent(it)
                        }
                        textFieldContent()
                    }
                    if (icon != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxHeight().width(55.dp).height(30.dp),
                        ) {
                            Icon(
                                painter = icon,
                                tint = PrimaryBase,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        show()
                                    }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(1.dp))

//            PopupContent(
//                width = boxWithConstraints.maxWidth,
//                isTextFocused = isFocused,
//                shape = shape,
//                onDismissed = {
//                    onClicked(false)
//                }
//            ) {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    content = {
//                        items(items = filteredItems, itemContent = { item ->
//                            dropDownContent(item)
//                        })
//                    }
//                )
//            }
        }

    }
}