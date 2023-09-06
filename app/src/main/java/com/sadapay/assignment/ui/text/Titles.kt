package com.sadapay.assignment.ui.text

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sadapay.assignment.ui.theme.DarkBluePrimary


@Composable
fun LargeTitle(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit? = null,
    textAlign: TextAlign? = null,
    color: Color? = null
) {
    val style = MaterialTheme.typography.titleLarge
    Text(
        text = text,
        style = style,
        color = color ?: DarkBluePrimary,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize ?: style.fontSize
    )
}

@Composable
fun MediumTitle(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit? = 14.sp,
    textAlign: TextAlign? = null,
    color: Color? = null,
    maxLines: Int = 3, allowCharacterLimitation: Boolean = false,
    charLength: Int = 25
) {
    val style = MaterialTheme.typography.titleMedium
    Text(
        text = if (allowCharacterLimitation) if (text.length > charLength) "${text.take(charLength)}..." else text else text,
        style = style,
        color = color ?: DarkBluePrimary,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize ?: style.fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SmallTitle(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit? = null,
    textAlign: TextAlign? = null,
    color: Color? = null,
    maxLines: Int? = null,
    textDecoration: TextDecoration = TextDecoration.None,
    lineHeight:TextUnit = 15.sp

) {
    val style = MaterialTheme.typography.titleSmall
    if (maxLines == null) {
        Text(
            text = text,
            style = style,
            color = color ?: DarkBluePrimary,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize ?: style.fontSize,
            textDecoration = textDecoration, lineHeight = lineHeight
        )
    } else {
        Text(
            text = text,
            style = style,
            color = color ?: DarkBluePrimary,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize ?: style.fontSize,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            textDecoration = textDecoration, lineHeight = lineHeight
        )
    }
}
