package acr.appcradle.shoppinglist.model

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.ui.graphics.Color

data class NewListData(
    val title: String? = null,
    val icon: Int = R.drawable.icon_snowflake,
    val iconColor: Color = Team2Colors.additionalColorGreen
)
