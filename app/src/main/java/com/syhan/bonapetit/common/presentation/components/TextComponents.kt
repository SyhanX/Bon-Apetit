package com.syhan.bonapetit.common.presentation.components

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
