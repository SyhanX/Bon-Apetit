package com.syhan.bonapetit.feature_recipes.presentation.recipes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.presentation.components.IconTextRow
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun RecipeCard(
    name: String = "",
    image: String = "",
    difficulty: String = "",
    cuisine: String = "",
    time: Int = 0,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp, top = 6.dp, bottom = 6.dp, end = 6.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                IconTextRow(
                    icon = R.drawable.ic_timer,
                    description = stringResource(R.string.time_minutes, time),
                    text = stringResource(R.string.time_minutes, time)
                )
                IconTextRow(
                    icon = R.drawable.ic_question_mark,
                    description = difficulty,
                    text = difficulty
                )
                IconTextRow(
                    icon = R.drawable.ic_cuisine,
                    description = cuisine,
                    text = cuisine
                )
            }
            Spacer(Modifier.width(8.dp))
            RecipeImage(name, image, Modifier.weight(1f))
        }
    }
}

@Composable
fun RecipeImage(
    name: String,
    image: String,
    modifier: Modifier = Modifier
) {
    val isLoading = remember { mutableStateOf(true) }
    Box(modifier) {
        AsyncImage(
            model = image,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            onLoading = { isLoading.value = true },
            onSuccess = { isLoading.value = false },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(130.dp)
        )
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onSecondaryContainer)
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false,
)
@Composable
private fun CardPreview() {
    BonApetitTheme {
        RecipeCard(
            name = "Bavarian Wurst",
            difficulty = "medium",
            cuisine = "Dutch",
            time = 30,
        )
    }
}