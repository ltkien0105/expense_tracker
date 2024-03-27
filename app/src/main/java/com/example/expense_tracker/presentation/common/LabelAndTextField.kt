package com.example.expense_tracker.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.ui.theme.DarkGreen
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.LightGray
import com.example.expense_tracker.ui.theme.ReplacementTheme

@Composable
fun LabelAndTextField(
    label: String,
    value: String,
    isReadOnly: Boolean = false,
    prefixText: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {
    Column {
        Text(
            text = label,
            style = ReplacementTheme.typography.labelSmall.copy(
                color = Gray
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = isReadOnly,
            prefix =  {
                if (prefixText != null)
                    Text(
                        text = prefixText,
                        style = ReplacementTheme.typography.bodyLarge.copy(
                            fontSize = 14.sp
                        )
                    )
            },
            trailingIcon = trailingIcon,
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
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )
        )
    }
}