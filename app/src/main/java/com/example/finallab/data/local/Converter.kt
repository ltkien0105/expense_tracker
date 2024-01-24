package com.example.finallab.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.finallab.data.local.transaction.TransactionType
import java.time.LocalDateTime

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun stringToTimestamp(timestamp: String): LocalDateTime = LocalDateTime.parse(timestamp)

    @TypeConverter
    fun timestampToString(time: LocalDateTime): String = time.toString()

    @TypeConverter
    fun stringToTransactionType(value: String): TransactionType = enumValueOf<TransactionType>(value)

    @TypeConverter
    fun transactionTypeToString(value: TransactionType): String = value.name
}