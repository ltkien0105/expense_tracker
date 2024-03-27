package com.example.expense_tracker.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.domain.model.DropdownItem
import com.example.expense_tracker.ui.theme.DarkGreen
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.LightGray
import com.example.expense_tracker.ui.theme.ReplacementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuContainer(
    expandState: Boolean,
    valueState: DropdownItem,
    label: String,
    onExpandedChange: (Boolean) -> Unit,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val leadingIconContent: @Composable (() -> Unit)? =
        if (valueState.icon != null) @Composable {
            {
                Icon(
                    painter = painterResource(id = valueState.icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        } else null

    Column {
        Text(
            text = label,
            style = ReplacementTheme.typography.labelSmall.copy(
                color = Gray
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        ExposedDropdownMenuBox(
            expanded = expandState,
            onExpandedChange = onExpandedChange
        ) {
            OutlinedTextField(
                value = valueState.label,
                onValueChange = {},
                readOnly = true,
                leadingIcon = leadingIconContent,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandState
                    )
                },
                textStyle = ReplacementTheme.typography.bodyLarge.copy(
                    fontSize = 14.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = LightGray,
                    focusedBorderColor = DarkGreen,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedTextColor = Gray,
                    focusedTextColor = DarkGreen,
                    unfocusedPrefixColor = Gray,
                    focusedPrefixColor = DarkGreen,
                    unfocusedTrailingIconColor = Gray,
                    focusedTrailingIconColor = DarkGreen
                ),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandState,
                onDismissRequest = {
                    onExpandedChange(false)
                },
            ) {
                content()
            }
        }
    }
}