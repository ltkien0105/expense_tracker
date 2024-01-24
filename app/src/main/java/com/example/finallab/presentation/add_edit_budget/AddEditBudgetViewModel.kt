package com.example.finallab.presentation.add_edit_budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finallab.data.local.budget.toAddEditBudgetState
import com.example.finallab.data.local.budget.toBudgetItem
import com.example.finallab.domain.use_case.add_edit_budget.AddEditBudgetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddEditBudgetViewModel @Inject constructor(
    private val addEditBudgetUseCases: AddEditBudgetUseCases
): ViewModel() {
    private val _addEditBudgetState = MutableStateFlow(AddEditBudgetState())
    val addEditBudgetState = _addEditBudgetState.asStateFlow()

    fun onEvent(event: AddEditBudgetEvent) {
        when(event) {
            is AddEditBudgetEvent.GetBudgetById -> {
                viewModelScope.launch {
                    addEditBudgetUseCases.getBudgetById(event.id).collect {
                        budgetEntity ->
                        _addEditBudgetState.value = budgetEntity?.toAddEditBudgetState()!!
                    }
                }

            }
            is AddEditBudgetEvent.SetAmount -> {
                _addEditBudgetState.update {
                    it.copy(amount = event.amount)
                }
            }
            is AddEditBudgetEvent.SetCategory -> {
                _addEditBudgetState.update {
                    it.copy(category = event.category)
                }
            }
            is AddEditBudgetEvent.SetRange -> {
                _addEditBudgetState.update {
                    it.copy(range = event.range)
                }
            }
            is AddEditBudgetEvent.SetCategoryDropdownExpanded -> {
                _addEditBudgetState.update {
                    it.copy(isCategoryDropdownExpand = event.isExpanded)
                }
            }
            is AddEditBudgetEvent.SetRangeDropdownExpanded -> {
                _addEditBudgetState.update {
                    it.copy(isRangeDropdownExpand = event.isExpanded)
                }
            }
            is AddEditBudgetEvent.SaveBudget -> {
                viewModelScope.launch {
                    if (_addEditBudgetState.value.id == 0) {
                        addEditBudgetUseCases.addBudget(
                            _addEditBudgetState.value.toBudgetEntity()
                        )
                    } else {
                        addEditBudgetUseCases.editBudget(
                            _addEditBudgetState.value.toBudgetEntity()
                        )
                    }
                }
            }
        }
    }
}