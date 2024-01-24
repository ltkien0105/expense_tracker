package com.example.finallab.presentation.add_edit_transaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finallab.R
import com.example.finallab.domain.model.CategoriesItem
import com.example.finallab.domain.model.DropdownItem
import com.example.finallab.data.local.transaction.TransactionType
import com.example.finallab.presentation.add_edit_transaction.component.DatePickerComponent
import com.example.finallab.presentation.common.DropdownItemContainer
import com.example.finallab.presentation.common.DropdownMenuContainer
import com.example.finallab.presentation.common.LabelAndTextField
import com.example.finallab.presentation.common.BackgroundScreen
import com.example.finallab.ui.theme.DarkGreen
import com.example.finallab.ui.theme.ReplacementTheme
import com.example.finallab.utils.getFormattedDate
import com.example.finallab.utils.toDropDownItem
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditTransactionScreen(
    snackbarHostState: SnackbarHostState,
    id: Int = 0,
    addEditTransactionViewModel: AddEditTransactionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val addEditState by addEditTransactionViewModel.addEditState.collectAsState()
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        if (id != 0) {
            addEditTransactionViewModel.onEvent(
                AddEditTransactionEvent.GetTransactionById(id, context)
            )
        }
    }

    val categoriesList = CategoriesItem.entries.filter {
        categoryItem ->
        if (addEditState.type.label == TransactionType.Income.name)
            categoryItem.type == TransactionType.Income
        else
            categoryItem.type == TransactionType.Expense
    }

    BackgroundScreen {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier.align(Alignment.Center),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    LabelAndTextField(
                        label = stringResource(id = R.string.name),
                        value = addEditState.name,
                        onValueChange = {
                            addEditTransactionViewModel.onEvent(AddEditTransactionEvent.SetName(it))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DropdownMenuContainer(
                        expandState = addEditState.isTypeDropdownExpanded,
                        valueState = addEditState.type,
                        label = "Type",
                        onExpandedChange = {
                            addEditTransactionViewModel.onEvent(
                                AddEditTransactionEvent.SetTypeDropdownExpanded(it)
                            )
                        }
                    ) {
                        TransactionType.entries.forEach {
                            transactionType ->
                            DropdownItemContainer(
                                dropdownItem = DropdownItem(
                                    null,
                                    transactionType.name
                                )
                            ) {
                                addEditTransactionViewModel.onEvent(
                                    AddEditTransactionEvent.SetTypeDropdownExpanded(false)
                                )
                                addEditTransactionViewModel.onEvent(
                                    AddEditTransactionEvent.SetType(
                                        DropdownItem(
                                            null,
                                            transactionType.name
                                        )
                                    )
                                )

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    DropdownMenuContainer(
                        expandState = addEditState.isCategoryDropdownExpanded,
                        valueState = addEditState.category,
                        label = "Categories",
                        onExpandedChange = {
                            addEditTransactionViewModel.onEvent(
                                AddEditTransactionEvent.SetCategoryDropdownExpanded(it)
                            )
                        }
                    ) {
                        categoriesList.forEach {
                            categoryItem ->
                            DropdownItemContainer(
                                dropdownItem = categoryItem.toDropDownItem(context)
                            ) {
                                addEditTransactionViewModel.onEvent(
                                    AddEditTransactionEvent.SetCategoryDropdownExpanded(false)
                                )
                                addEditTransactionViewModel.onEvent(
                                    AddEditTransactionEvent.SetCategory(
                                        categoryItem.toDropDownItem(context)
                                    )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LabelAndTextField(
                        label = stringResource(id = R.string.amount),
                        value = addEditState.amount,
                        prefixText = "$",
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    addEditTransactionViewModel.onEvent(AddEditTransactionEvent.ClearAmount)
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.clear),
                                    style = ReplacementTheme.typography.bodyLarge.copy(
                                        fontSize = 14.sp
                                    )
                                )
                            }

                        },
                        keyboardType = KeyboardType.Number,
                        onValueChange = {
                            addEditTransactionViewModel.onEvent(AddEditTransactionEvent.SetAmount(it))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LabelAndTextField(
                        label = stringResource(id = R.string.label_date),
                        value = addEditState.date.getFormattedDate(),
                        isReadOnly = true,
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    addEditTransactionViewModel.onEvent(
                                        AddEditTransactionEvent.SetDatePickerShowed(true)
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null
                                )
                            }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            addEditTransactionViewModel.onEvent(
                                AddEditTransactionEvent.SaveTransaction(context)
                            )

                            val message = if (addEditState.id == 0) {
                                context.getString(
                                    R.string.added_successfully_notification,
                                    context.getString(R.string.transaction)
                                )
                            } else {
                                context.getString(
                                    R.string.updated_successfully_notification,
                                    context.getString(R.string.transaction)
                                )
                            }

                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(
                                    message = message,
                                    withDismissAction = false,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkGreen
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = stringResource(id = R.string.confirm))
                    }
                    DatePickerComponent(
                        viewModel = addEditTransactionViewModel,
                        isVisible = addEditState.isDatePickerShowed
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddEditScreenPreview() {
    AddEditTransactionScreen(snackbarHostState = remember {
        SnackbarHostState()
    })
}