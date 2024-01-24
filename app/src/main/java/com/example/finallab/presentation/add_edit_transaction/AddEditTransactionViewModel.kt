package com.example.finallab.presentation.add_edit_transaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finallab.domain.use_case.add_edit_transaction.AddEditTransactionUseCases
import com.example.finallab.domain.use_case.validation.ValidationUseCases
import com.example.finallab.utils.toAddEditState
import com.example.finallab.utils.toTransactionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    private val addEditTransactionUseCases: AddEditTransactionUseCases,
    private val validationUseCases: ValidationUseCases
): ViewModel() {

    private val _addEditTransactionState = MutableStateFlow(AddEditTransactionState())
    val addEditState = _addEditTransactionState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddEditTransactionEvent) {
        when(event) {
            is AddEditTransactionEvent.SetName -> {
                _addEditTransactionState.update {
                    it.copy(name = event.name)
                }
            }
            is AddEditTransactionEvent.SetType -> {
                _addEditTransactionState.update {
                    it.copy(type = event.type)
                }
            }
            is AddEditTransactionEvent.SetCategory -> {
                _addEditTransactionState.update {
                    it.copy(category = event.category)
                }
            }
            is AddEditTransactionEvent.SetAmount -> {
                _addEditTransactionState.update {
                    it.copy(amount = event.amount)
                }
            }
            is AddEditTransactionEvent.SetDate -> {
                _addEditTransactionState.update {
                    it.copy(date = event.date)
                }
            }
            AddEditTransactionEvent.ClearAmount -> {
                _addEditTransactionState.update {
                    it.copy(amount = "")
                }
            }
            is AddEditTransactionEvent.SaveTransaction -> {
                if (!validationUseCases.validationAmount(_addEditTransactionState.value.amount).isSuccess)
                    return

                viewModelScope.launch {
                    if (_addEditTransactionState.value.id == 0) {
                        addEditTransactionUseCases.insert(
                            _addEditTransactionState.value.toTransactionItem(event.context)
                        )
                    } else {
                        addEditTransactionUseCases.update(
                            _addEditTransactionState.value.toTransactionItem(event.context)
                        )
                    }
                }
            }

            is AddEditTransactionEvent.SetTypeDropdownExpanded -> {
                _addEditTransactionState.update {
                    it.copy(isTypeDropdownExpanded = event.isExpanded)
                }
            }

            is AddEditTransactionEvent.SetCategoryDropdownExpanded -> {
                _addEditTransactionState.update {
                    it.copy(isCategoryDropdownExpanded = event.isExpanded)
                }
            }

            is AddEditTransactionEvent.GetTransactionById -> {
                viewModelScope.launch {
                    val transaction = addEditTransactionUseCases.getById((event.id))
                    transaction.collect {
                        transactionItem ->
                        _addEditTransactionState.value = transactionItem?.toAddEditState(event.context) ?: AddEditTransactionState()
                    }
                }
            }

            is AddEditTransactionEvent.SetDatePickerShowed -> {
                _addEditTransactionState.update {
                    it.copy(isDatePickerShowed = event.isShowed)
                }
            }
        }
    }
}
