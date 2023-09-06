package com.sadapay.assignment.utils

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 0.dp)
}

fun Modifier.defaultModifier(widthPercentage: Float = 1f): Modifier {
    return this.fillMaxWidth(widthPercentage)
        .wrapContentHeight()
        .padding(16.dp,8.dp)
}

fun Modifier.card(): Modifier {
    return this.padding(16.dp, 0.dp, 16.dp, 8.dp)
}

fun Modifier.contextMenu(): Modifier {
    return this.wrapContentWidth()
}

fun Modifier.dropdownSelector(): Modifier {
    return this.fillMaxWidth()
}

fun Modifier.textButtonModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 4.dp)
}
fun Modifier.homePageModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp)
}

fun Modifier.toolbarActions(end: Boolean = false): Modifier {
    return this.wrapContentSize(if(end)Alignment.TopEnd else Alignment.TopStart, false)
}

fun Modifier.actionBarModifier(): Modifier {
    return this.fillMaxWidth().height(56.dp).padding(8.dp,4.dp)
}

fun Modifier.spacer(): Modifier {
    return this.fillMaxWidth().padding(25.dp)
}

fun Modifier.smallSpacer(): Modifier {
    return this.fillMaxWidth().height(8.dp)
}
