package com.example.finallab.presentation.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.finallab.domain.model.DropdownItem
import com.example.finallab.ui.theme.Gray
import com.example.finallab.ui.theme.ReplacementTheme

@Composable
fun DropdownItemContainer(
    dropdownItem: DropdownItem,
    onClick: () -> Unit = {}
) {
    val leadingIcon: @Composable (() -> Unit)? =
        if (dropdownItem.icon != null)
            @Composable {
                {
                    Icon(
                        painter = painterResource(id = dropdownItem.icon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        else null

    DropdownMenuItem(
        leadingIcon = leadingIcon,
        text = {
            Text(
                text = dropdownItem.label,
                style = ReplacementTheme.typography.bodyLarge.copy(
                    fontSize = 14.sp,
                    color = Gray
                )
            )
        },
        onClick = onClick
    )
}