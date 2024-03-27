package com.example.expense_tracker.presentation.add_edit_budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expense_tracker.R
import com.example.expense_tracker.domain.model.CategoriesItem
import com.example.expense_tracker.domain.model.DropdownItem
import com.example.expense_tracker.domain.model.TimeRange
import com.example.expense_tracker.presentation.common.BackgroundScreen
import com.example.expense_tracker.presentation.common.DropdownItemContainer
import com.example.expense_tracker.presentation.common.DropdownMenuContainer
import com.example.expense_tracker.presentation.common.LabelAndTextField
import com.example.expense_tracker.ui.theme.DarkGreen
import com.example.expense_tracker.ui.theme.ReplacementTheme
import com.example.expense_tracker.utils.getTimeRangeString
import com.example.expense_tracker.utils.toDropDownItem
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditBudgetScreen(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    id: Int = 0,
    addEditBudgetViewModel: AddEditBudgetViewModel = hiltViewModel()
) {
    val addEditBudgetState by addEditBudgetViewModel.addEditBudgetState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        if (id != 0) {
            addEditBudgetViewModel.onEvent(
                AddEditBudgetEvent.GetBudgetById(id)
            )
        }
    }

    BackgroundScreen {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        stringResource(R.string.add_edit_budget),
                        style = ReplacementTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    DropdownMenuContainer(
                        expandState = addEditBudgetState.isCategoryDropdownExpand,
                        valueState = addEditBudgetState.category,
                        label = stringResource(R.string.category),
                        onExpandedChange = {
                            addEditBudgetViewModel.onEvent(
                                AddEditBudgetEvent.SetCategoryDropdownExpanded(
                                    !addEditBudgetState.isCategoryDropdownExpand
                                )
                            )
                        }
                    ) {
                        CategoriesItem.entries.forEach {
                            val categoryDropdownItem = it.toDropDownItem(context)
                            DropdownItemContainer(
                                dropdownItem = categoryDropdownItem,
                                onClick = {
                                    addEditBudgetViewModel.onEvent(
                                        AddEditBudgetEvent.SetCategory(categoryDropdownItem)
                                    )
                                    addEditBudgetViewModel.onEvent(
                                        AddEditBudgetEvent.SetCategoryDropdownExpanded(false)
                                    )
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LabelAndTextField(
                        label = stringResource(R.string.amount),
                        value = addEditBudgetState.amount,
                        keyboardType = KeyboardType.Number,
                    ) {
                        addEditBudgetViewModel.onEvent(AddEditBudgetEvent.SetAmount(it))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    DropdownMenuContainer(
                        expandState = addEditBudgetState.isRangeDropdownExpand,
                        valueState = addEditBudgetState.range,
                        label = stringResource(R.string.time_range),
                        onExpandedChange = {
                            addEditBudgetViewModel.onEvent(
                                AddEditBudgetEvent.SetRangeDropdownExpanded(
                                    !addEditBudgetState.isRangeDropdownExpand
                                )
                            )
                        }
                    ) {
                        TimeRange.entries.forEach {
                            DropdownItemContainer(
                                dropdownItem = DropdownItem(
                                    null,
                                    it.getTimeRangeString()
                                )
                            ) {
                                addEditBudgetViewModel.onEvent(
                                    AddEditBudgetEvent.SetRange(
                                        DropdownItem(
                                            null,
                                            it.getTimeRangeString()
                                        )
                                    )
                                )
                                addEditBudgetViewModel.onEvent(
                                    AddEditBudgetEvent.SetRangeDropdownExpanded(false)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            addEditBudgetViewModel.onEvent(AddEditBudgetEvent.SaveBudget)

                            val message = if (addEditBudgetState.id == 0) {
                                context.getString(
                                    R.string.added_successfully_notification,
                                    context.getString(R.string.budget)
                                )
                            } else {
                                context.getString(
                                    R.string.updated_successfully_notification,
                                    context.getString(R.string.budget)
                                )
                            }
                            println(message)
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
                        Text(
                            if (id != 0) stringResource(id = R.string.update) else stringResource(R.string.create)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
//                            addEditBudgetViewModel.onEvent(AddEditBudgetEvent.SaveBudget)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkGreen
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewAddEditBudgetScreen() {
    AddEditBudgetScreen(id = 0)
}