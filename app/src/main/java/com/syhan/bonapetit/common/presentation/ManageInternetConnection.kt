package com.syhan.bonapetit.common.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.data.NetworkResponse

@Composable
fun ManageInternetConnection(
    response: NetworkResponse,
    retryAction: () -> Unit,
    onSuccess: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (response) {
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
                Text(
                    text = stringResource(R.string.loading),
                    fontSize = 20.sp
                )
            }

            NetworkResponse.UnexpectedError, NetworkResponse.ConnectionError -> {
                if (response == NetworkResponse.ConnectionError) {
                    Text(
                        text = stringResource(R.string.error_no_internet),
                        fontSize = 20.sp
                    )
                } else {
                    Text(
                        text = stringResource(R.string.error_unexpected),
                        fontSize = 20.sp
                    )
                }
                Button(onClick = { retryAction() }) {
                    Text(text = stringResource(R.string.action_retry))
                }
            }

            NetworkResponse.Success -> {
                onSuccess()
            }
        }
    }
}