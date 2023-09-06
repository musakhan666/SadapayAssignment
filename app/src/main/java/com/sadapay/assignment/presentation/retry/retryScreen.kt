package com.sadapay.assignment.presentation.retry



import androidx.compose.runtime.Composable
import com.sadapay.assignment.ui.text.LargeTitle
import com.sadapay.assignment.ui.text.PrimaryButton
import com.sadapay.assignment.ui.text.SmallTitle
import com.sadapay.assignment.ui.theme.GreenLight
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadapay.assignment.ui.Loader
import com.sadapay.assignment.R

@Composable
fun retryScreen(retry:() -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {

                Loader(
                    modifier = Modifier.align(Alignment.CenterHorizontally).aspectRatio(1f)
                        .fillMaxWidth()
                )
                LargeTitle(
                    Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.something_went_wrong),
                    color = Color.Black,
                    fontSize = 24.sp
                )

                SmallTitle(
                    Modifier.align(Alignment.CenterHorizontally).padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.retry_body),
                    color = Color.LightGray,
                    textAlign = TextAlign.Center, fontSize = 16.sp
                )

                Spacer(modifier = Modifier.weight(1f)) // Occupy remaining vertical space
                PrimaryButton(
                    modifier = Modifier
                        .padding(vertical = 35.dp, horizontal = 15.dp)
                        .fillMaxWidth()
                        .height(50.dp).border(
                            width = 1.dp,
                            color = GreenLight,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    text = stringResource(R.string.retry_button),
                    shape = RoundedCornerShape(5.dp),
                    backgroundColor = Color.White, textColor = GreenLight
                ) {
                    // Handle the retry action here
                    retry.invoke()
                }
            }


        }
    }
}