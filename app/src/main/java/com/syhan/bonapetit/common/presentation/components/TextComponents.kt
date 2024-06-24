package com.syhan.bonapetit.common.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//TODO: this is a load of shit and needs to be refactored

@Composable
fun IconTextRow(
    @DrawableRes icon: Int,
    description: String,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = description,
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun NormalText(
    text: String,
    fontSize: TextUnit = 18.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun BoldText(
    text: String,
    fontSize: TextUnit = 18.sp,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun SemiboldText(
    text: String,
    fontSize: TextUnit = 18.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun SemiboldNormalTextRow(
    boldText: String,
    normalText: String,
) {
    Row {
        SemiboldText(text = "$boldText: ")
        NormalText(text = normalText)
    }
}

@Composable
fun SemiboldNormalTextColumn(
    boldText: String,
    normalText: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SemiboldText(text = "$boldText: ")
        NormalText(text = normalText)
    }
}