package com.syhan.bonapetit.feature_recipes.presentation.details.compontents

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.domain.showToastMessage

@Composable
fun CopyToClipboardButton(
    clipboardManager: ClipboardManager = LocalClipboardManager.current,
    context: Context = LocalContext.current,
    string: String
) {
    IconButton(onClick = {
        clipboardManager.setText(
            AnnotatedString(string)
        )
        showToastMessage(context)
    }) {
        Icon(
            painter = painterResource(R.drawable.ic_content_copy),
            contentDescription = stringResource(R.string.action_copy_text)
        )
    }
}