package com.syhan.bonapetit.feature_recipes.presentation.recipes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardDefaults
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
    name: String,
    image: String = "",
    servings: Int,
    cuisine: String,
    time: Int,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp, 6.dp)
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
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                IconTextRow(
                    icon = R.drawable.ic_globe,
                    description = cuisine,
                    text = cuisine
                )
                IconTextRow(
                    icon = R.drawable.ic_time,
                    description = stringResource(R.string.time_minutes, time),
                    text = stringResource(R.string.time_minutes, time)
                )
                IconTextRow(
                    icon = R.drawable.ic_dish_hollow,
                    description = stringResource(R.string.servings_count, servings),
                    text = stringResource(R.string.servings_count, servings)
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
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(8.dp)
                )
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

@Preview
@Composable
private fun CardPreview() {
    BonApetitTheme {
        RecipeCard(
            name = "Bavarian Wurst",
            servings = 4,
            cuisine = "Dutch",
            time = 30,
        )
    }
}

@Preview
@Composable
private fun CardDarkPreview() {
    BonApetitTheme(
        darkTheme = true
    ) {
        RecipeCard(
            name = "Apfelluftwaffegesetz",
            servings = 4,
            cuisine = "Dutch",
            time = 30,
        )
    }
}