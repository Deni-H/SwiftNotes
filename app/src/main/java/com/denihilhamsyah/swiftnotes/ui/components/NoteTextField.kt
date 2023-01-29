package com.denihilhamsyah.swiftnotes.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: @Composable () -> Unit,
    imeAction: ImeAction
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeHolder,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun NoteTextFieldPreview() {

    val title: MutableState<String> = remember {
        mutableStateOf("")
    }

    NoteTextField(
        value = title.value,
        onValueChange = {
            title.value = it
        },
        placeHolder = {
            Text(text = "Title")
        },
        imeAction = ImeAction.None
    )
}