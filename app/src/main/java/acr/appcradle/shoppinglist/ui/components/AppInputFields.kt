package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

object AppInputFields {
    @Composable
    fun MainInputField(
        modifier: Modifier = Modifier,
        placeholderText: String,
        isSearchIconNeeded: Boolean = false,
        onValueChange: (String) -> Unit = {},
        editedValue: String? = null,
        isNumeric: Boolean = false,
        isError: Boolean = false,
    ) {
        var inputText by rememberSaveable { mutableStateOf(editedValue ?: "") }
        OutlinedTextField(
            modifier = modifier,
            value = inputText,
            isError = isError,
            placeholder = { Text(text = placeholderText) },
            onValueChange = {
                inputText = it
                onValueChange(inputText)
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor     = Team2Colors.team2color_red,
                errorCursorColor        = Team2Colors.team2color_red,
                errorLabelColor         = Team2Colors.team2color_red
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isNumeric) KeyboardType.Number else KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                if(isError) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error_textfield),
                        contentDescription = null,
                        tint = Team2Colors.team2color_red
                    )
                } else if (inputText != "") {
                    Icon(
                        modifier = Modifier.clickable { inputText = "" },
                        imageVector = Icons.Default.Clear, contentDescription = null
                    )
                }
            },
            leadingIcon = {
                if (isSearchIconNeeded)
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = null
                    )
            },
        )
    }
}

