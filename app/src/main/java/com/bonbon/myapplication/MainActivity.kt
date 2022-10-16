package com.bonbon.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bonbon.library.ActionChip
import com.bonbon.library.ChipItem
import com.bonbon.library.DropDownChipsTextField
import com.bonbon.library.OutlineChipsTextField
import com.bonbon.myapplication.ui.theme.*
import com.google.accompanist.insets.ProvideWindowInsets


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposechipTheme {
                ProvideWindowInsets(
                ) {
                    val scrollState = rememberScrollState()
                    var selectedItems = SnapshotStateList<ChipItem>()
                    var asd = mutableListOf<String>("asd", "asdasd", "jejwjwe")
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Box(
                            modifier = Modifier.scrollable(
                                scrollState,
                                orientation = Orientation.Vertical
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                OutlineChipsTextField(
                                    label = "Email",
                                    color = Neutral90,
                                    icon = null,
                                ) {
//                                    for (i in it) {
//                                        Log.d("TAG", "onCreate: ${i.value}")
//                                    }
//                                    selectedItems = it
                                }
                                DropDownChipsTextField(
                                    label = "Items",
                                    color = Neutral90,
                                    icon = painterResource(id = R.drawable.ic_baseline_android_24),
                                    selectedItems = asd,
                                    show = {
                                        asd.add("hehehhee")
                                    }
                                ) {
//                                    for (i in it) {
//                                        Log.d("TAG", "onCreate: ${i.value}")
//                                    }
//                                    selectedItems = it
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@ExperimentalAnimationApi
//@ExperimentalComposeUiApi
//@Composable
//private fun OutLineChipTextViewDemo(
//    label: String
//) {
//    var text by remember {
//        mutableStateOf("")
//    }
//
//    Text(
//        text = label,
//        modifier = Modifier.padding(bottom = 4.dp)
//    )
//    val selectedItems = remember {
//        mutableStateListOf<ChipItem>()
//    }
//
//    OutLinedTextChipView(
//        modifier = Modifier,
//        chipItems = selectedItems,
//        shape = RoundedCornerShape(6.dp),
//        text = text,
//        focusColor = PrimaryBase,
//        unFocusColor = Neutral40,
//        onValueChange = {
//            text = if (it.trim().isNotEmpty() && it.contains(TriggerSeparator.Space.value) || it.contains(TriggerSeparator.Enter.value)) {
//                val trimmedText = text.trim()
//                selectedItems.add(ChipItem(trimmedText))
//                ""
//            } else {
//                it
//            }
//        },
//        chipContent = {
//            ActionChip(
//                text = it.value,
//                closeIcon = rememberVectorPainter(image = Icons.Default.Close),
//                avatar = it.icon?.let { it1 -> painterResource(id = it1) },
//                color = Neutral20,
//
//                shape = RoundedCornerShape(18.dp)
//            ) {
//                selectedItems.remove(it)
//            }
//        },
//        onKeyEvent = {
//            if (it.key == Key.Backspace) {
//                if (text.isEmpty() && selectedItems.count() > 0) {
//                    selectedItems.removeAt(0.coerceAtLeast(selectedItems.count() - 1))
//                }
//            }
//        },
//        textStyle = TextStyle.Default.copy(color = Neutral90)
//    ) {
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                selectedItems.add(it)
//            }
//            .padding(8.dp)) {
//            Text(text = it.value)
//        }
//    }
//}
