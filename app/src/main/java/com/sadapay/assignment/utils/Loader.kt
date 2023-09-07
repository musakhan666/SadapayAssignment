package com.sadapay.assignment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.sadapay.assignment.R

@Composable
fun Loader(modifier: Modifier = Modifier, isVisible: Boolean = true) {
    if(isVisible) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.retry))
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
        )
        LottieAnimation(
            composition,
            modifier = modifier,
            progress = progress
        )
    }
}