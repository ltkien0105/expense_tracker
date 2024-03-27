package com.example.expense_tracker.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.expense_tracker.domain.model.CategoriesItem
import com.example.expense_tracker.domain.model.DropdownItem
import com.example.expense_tracker.data.local.transaction.TransactionEntity
import com.example.expense_tracker.data.local.transaction.TransactionType
import com.example.expense_tracker.domain.model.TimeRange
import com.example.expense_tracker.presentation.add_edit_transaction.AddEditTransactionState
import com.patrykandpatrick.vico.core.entry.entriesOf
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CategoriesItem.toDropDownItem(context: Context): DropdownItem {
    return DropdownItem(
        this.icon,
        context.getString(this.category)
    )
}

fun AddEditTransactionState.toTransactionItem(context: Context): TransactionEntity {
    val resources = context.resources
    val resourceName = resources.getResourceEntryName(category.icon ?: 0)

    return TransactionEntity(
        id = this.id,
        iconName = resourceName,
        title = this.name,
        date = this.date,
        category = this.category.label,
        type = TransactionType.valueOf(this.type.label),
        amount = amount.toFloat()
    )
}

fun TransactionEntity.toDropdownItem(context: Context): DropdownItem {
    val resources = context.resources

    val drawableResource = CategoriesItem.entries.find {
        resources.getResourceEntryName(it.icon) == this.iconName
    }?.icon

    return DropdownItem(
        drawableResource,
        this.iconName
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun TransactionEntity.toAddEditState(context: Context): AddEditTransactionState {
    val resources = context.resources

    val categoriesItem = CategoriesItem.entries.find {
        resources.getResourceEntryName(it.icon) == this.iconName
    }

    return AddEditTransactionState(
        id = this.id,
        name = this.title,
        type = DropdownItem(null, this.type.name),
        category = DropdownItem(categoriesItem!!.icon, context.getString(categoriesItem.category)),
        amount = this.amount.toString(),
        date = this.date
    )

}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.getFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun TimeRange.getTimeRangeString(): String {
    return "This ${this.name}\n(${this.startTime.getFormattedDate()} - ${this.endTime.getFormattedDate()})"
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val list = List(30) { it + 1 }
    val v = 0

    val entryList = when (v) {
        0, 2 -> list.map { it }
        else -> list.chunked(7) {
            entriesOf(*it.toTypedArray())
        }
    }
    println(entryList)
}