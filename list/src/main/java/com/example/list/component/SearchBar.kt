package com.example.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    textValue: TextFieldValue = TextFieldValue(""),
    onSearchQueryChanged: (TextFieldValue) -> Unit = {},
) {
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = onSearchQueryChanged,
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
            },
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )
        )
    }

}