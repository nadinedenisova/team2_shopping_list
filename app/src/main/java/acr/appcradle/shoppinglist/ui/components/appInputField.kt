package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun appInputField(
    modifier: Modifier = Modifier,
    placeholderText: String,
    isSearchIconNeeded: Boolean = false,
    onValueChange: (String) -> Unit = {}
): String {
    var inputText by remember { mutableStateOf("") }
    TextField(
        modifier = modifier,
        value = inputText,
        placeholder = { Text(text = placeholderText) },
        onValueChange = {
            inputText = it
            onValueChange(inputText)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        trailingIcon = {
            if (inputText != "")
                Icon(
                    modifier = Modifier.clickable { inputText = "" },
                    imageVector = Icons.Default.Clear, contentDescription = null
                )
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
                isSearchIconNeeded = false
            )
        }
    }
}