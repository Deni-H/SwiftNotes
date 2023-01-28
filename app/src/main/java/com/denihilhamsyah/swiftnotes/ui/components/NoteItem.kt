package com.denihilhamsyah.swiftnotes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denihilhamsyah.swiftnotes.domain.model.Note

@Composable
fun NoteItem(
    note: Note
) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(color = Color.Red, width = 1.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = note.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.description!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    NoteItem(Note(
        id = 1,
        title = "Hello World",
        description = "This is an example"
    ))
}