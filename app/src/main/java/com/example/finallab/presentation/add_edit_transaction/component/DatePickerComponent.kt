package com.example.finallab.presentation.add_edit_transaction.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.example.finallab.presentation.add_edit_transaction.AddEditTransactionEvent
import com.example.finallab.presentation.add_edit_transaction.AddEditTransactionViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    viewModel: AddEditTransactionViewModel,
    isVisible: Boolean
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli(),
    )

    if (isVisible) {
        DatePickerDialog(
            onDismissRequest = {
                viewModel.onEvent(
                    AddEditTransactionEvent.SetDatePickerShowed(false)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.onEvent(
                            AddEditTransactionEvent.SetDate(
                                LocalDateTime.ofInstant(
                                    Instant.ofEpochMilli(datePickerState.selectedDateMillis!!),
                                    ZoneOffset.UTC
                                )
                            )
                        )
                        viewModel.onEvent(
                            AddEditTransactionEvent.SetDatePickerShowed(false)
                        )
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.onEvent(
                            AddEditTransactionEvent.SetDatePickerShowed(false)
                        )
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                dateFormatter = DatePickerDefaults.dateFormatter(
                    yearSelectionSkeleton = "MM/yyyy",
                    selectedDateSkeleton = "dd/MM/yyyy"
                )
            )
        }
    }
}