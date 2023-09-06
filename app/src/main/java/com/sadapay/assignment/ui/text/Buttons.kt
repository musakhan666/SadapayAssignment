package com.sadapay.assignment.ui.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadapay.assignment.ui.theme.GoldPrimary
import com.sadapay.assignment.ui.theme.White
import com.sadapay.assignment.utils.textButtonModifier


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "",
    loading: Boolean = false,
    enabled: Boolean = true,
    backgroundColor: Color = GoldPrimary,
    content: @Composable (RowScope.() -> Unit)? = null,
    fontSize: TextUnit? = null,
    textColor: Color = White,
    shape: RoundedCornerShape = CircleShape,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = textColor,
            disabledBackgroundColor = backgroundColor.copy(alpha = 0.5f)
        ),

        modifier = modifier, onClick = onClick, enabled = enabled && !loading, content = {
            if (content == null) TextButton(
                text = text,
                textColor = textColor,
                modifier = Modifier.textButtonModifier(),
                fontSize = fontSize
            )
            content?.invoke(this)
        }, shape = shape
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "",
    loading: Boolean = false,
    enabled: Boolean = true,
    outLineColor: Color = Color.White,
    backgroundColor: Color = Color.White,
    content: @Composable (RowScope.() -> Unit)? = null,
    fontSize: TextUnit? = null,
    textColor: Color = White,
    shape: RoundedCornerShape = CircleShape,
    onClick: () -> Unit
) {
    OutlinedButton(
        elevation = ButtonDefaults.elevation(2.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = backgroundColor,
            disabledContentColor = textColor.copy(alpha = 0.5f),
            contentColor = textColor,
        ),

        border = BorderStroke(1.dp, outLineColor),
        modifier = modifier,
        onClick = onClick,
        enabled = enabled && !loading,
        content = {
            if (content == null) TextButton(
                text = text,
                textColor = textColor,
                modifier = Modifier.textButtonModifier(),
                fontSize = fontSize
            )
            content?.invoke(this)
        },
        shape = shape

    )
}

@Composable
fun ButtonWithTrailingIcon(
    text: String,
    modifier: Modifier,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit,
    trailingIcon: Int
) {
    Card(
        modifier = modifier.clickable {
            onClick.invoke()
        }, elevation = 3.dp, backgroundColor = backgroundColor
    ) {
        Box(modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize()) {
            MediumTitle(
                modifier = Modifier.align(Alignment.CenterStart),
                text = text,
                fontSize = 16.sp
            )

            Icon(
                painter = painterResource(trailingIcon),
                modifier = Modifier.size(25.dp).align(Alignment.CenterEnd),
                contentDescription = "next",
                tint = GoldPrimary
            )
        }
    }
}