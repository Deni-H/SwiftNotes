package com.denihilhamsyah.swiftnotes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.denihilhamsyah.swiftnotes.domain.model.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    note: Note,
    selected: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                top = 8.dp,
                start = 4.dp,
                end = 4.dp
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        border = getBorder(selected),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            if (!note.title.isNullOrEmpty()) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (!note.title.isNullOrEmpty() && !note.description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (!note.description.isNullOrEmpty()) {
                Text(
                    text = note.description,
                    style = MaterialTheme.typography.body2,
                    maxLines = 20,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun getBorder(isSelected: Boolean): BorderStroke {
    if (isSelected) {
        return BorderStroke(
            color = Color.Blue,
            width = 2.dp
        )
    }
    return BorderStroke(
        color = Color.LightGray,
        width = 0.5.dp
    )
}

