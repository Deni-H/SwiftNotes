package com.denihilhamsyah.swiftnotes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.denihilhamsyah.swiftnotes.domain.model.Note

@Composable
fun NoteItem(
    note: Note
) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(color = Color.LightGray, width = 0.5.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Text(
                text = note.title,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.description!!,
                style = MaterialTheme.typography.body2
            )
        }
    }
}