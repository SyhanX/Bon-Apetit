package com.syhan.bonapetit.common.domain

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.presentation.components.SemiboldText
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import java.math.RoundingMode

//I'm so proud of this
@Composable
fun FiveStarRating(rating: Double) {
    val maxRating = 5.0
    val minRating = 0.0
    if (rating > maxRating || rating < minRating) return
    val fullStarCount = rating.toInt()
    val emptyStarCount = (maxRating - rating).toInt()
    val roundedUpRemainder =
        (rating - fullStarCount).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
    val roundedUpRating = rating.toBigDecimal().setScale(1, RoundingMode.UP).toString()

    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(fullStarCount) { FullStar() }
        when {
            roundedUpRemainder >= 0.5 -> {
                HalfStar()
            }

            roundedUpRemainder < 0.5 && roundedUpRemainder > 0.0 && emptyStarCount < 5 -> {
                EmptyStar()
            }
        }
        repeat(emptyStarCount) { EmptyStar() }
        Spacer(Modifier.width(6.dp))
        SemiboldText(text = roundedUpRating)
    }
}

@Composable
fun FullStar() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun HalfStar() {
    Icon(
        painter = painterResource(R.drawable.ic_star_half),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun EmptyStar() {
    Icon(
        painter = painterResource(R.drawable.ic_star_border),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.secondary
    )
}

@Preview
@Composable
private fun RatingPreview() {
    BonApetitTheme(darkTheme = true) {
        FiveStarRating(2.543)
    }
}