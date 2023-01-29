package com.denihilhamsyah.swiftnotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.denihilhamsyah.swiftnotes.R

@Composable
fun TopBar(
    onClickBack: () -> Unit,
    content: @Composable () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onClickBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back_button"
            )
        }
        content()
    }
}