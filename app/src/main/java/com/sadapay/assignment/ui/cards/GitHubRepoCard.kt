package com.sadapay.assignment.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sadapay.assignment.R
import com.sadapay.assignment.domain.model.search.GitHubRepo
import com.sadapay.assignment.ui.text.MediumTitle
import com.sadapay.assignment.ui.text.SmallTitle

@Composable
fun GitHubRepoCard(item: GitHubRepo, itemClicked: () -> Unit) {
    Card(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 15.dp).fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = itemClicked),
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        val context = LocalContext.current

        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .data(item.owner?.avatar_url)
                    .crossfade(true)
                    .placeholder(R.drawable.place_holder_img)
                    .build(),
                contentDescription = "user image",
                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.place_holder_img),
                modifier = Modifier
                    .padding(15.dp)
                    .size(50.dp).clip(CircleShape),
            )
            Column(modifier = Modifier.fillMaxHeight()) {
                SmallTitle(
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                    text = item.name ?: "",
                    fontSize = 12.sp
                )
                MediumTitle(
                    modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth().weight(1f),
                    text = item.full_name ?: "",
                    fontSize = 14.sp
                )
            }

        }

    }

}