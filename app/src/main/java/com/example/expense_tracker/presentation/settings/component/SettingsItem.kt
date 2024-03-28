package com.example.expense_tracker.presentation.settings.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.ReplacementTheme

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_theme,
    title: String = "Dark Mode",
    contentDescription: String = "Theme Icon",
    isChecked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = ReplacementTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = ReplacementTheme.typography.labelMedium,
                color = ReplacementTheme.colorScheme.onBackground
            )
        }
        if (onCheckedChange != null)
            Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}

@Preview
@Composable
fun SettingsItemPreview() {
    SettingsItem()
}