package com.sadapay.assignment.ui.dialogs


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sadapay.assignment.ui.Loader

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = {}) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Transparent,
            modifier = Modifier.wrapContentHeight().fillMaxWidth(0.80f)

        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                ) {

                    Loader(
                        modifier = Modifier.align(Alignment.CenterHorizontally).aspectRatio(1f)
                            .fillMaxWidth(0.5f)
                    )

                }
            }
        }
    }
}





