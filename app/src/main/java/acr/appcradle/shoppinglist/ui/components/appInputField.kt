package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun appInputField(
    placeholderText: String,
    isSearchIconNeeded: Boolean = false
): String {
    var inputText by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = inputText,
        placeholder = { Text(text = placeholderText) },
        onValueChange = { inputText = it },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            if (inputText != "")
                Icon(
                    modifier = Modifier.clickable { inputText = "" },
                    imageVector = Icons.Default.Clear, contentDescription = null
                )
            else
                null
        },
        leadingIcon = {
            if (isSearchIconNeeded)
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
        }
    )
    return inputText
}


@ThemePreviews
@Composable
fun Preview() {
    ShoppingListTheme {
        Surface {
            appInputField(
                placeholderText = "Введите название списка",
                isSearchIconNeeded = true
            )
        }
    }
}