package com.example.finallab.presentation.common

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finallab.data.local.transaction.TransactionEntity
import com.example.finallab.data.local.transaction.TransactionType
import com.example.finallab.ui.theme.Gray
import com.example.finallab.ui.theme.LightGreen
import com.example.finallab.ui.theme.LightWhiteGreen
import com.example.finallab.ui.theme.Red
import com.example.finallab.ui.theme.ReplacementTheme
import com.example.finallab.utils.getFormattedDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("DiscouragedApi")
@Composable
fun TransactionItem(
    transactionEntity: TransactionEntity,
    modifier: Modifier = Modifier
) {
    val isIncome = transactionEntity.type == TransactionType.Income
    val dateFormatted = transactionEntity.date.getFormattedDate()
    val context = LocalContext.current
    val textValue = if (isIncome) {
        "+ $ ${transactionEntity.amount}"
    } else {
        "- $ ${transactionEntity.amount}"
    }


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(
                    id = context.resources.getIdentifier(
                        transactionEntity.iconName,
                        "drawable",
                        context.packageName
                    )
                ),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = LightWhiteGreen,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    transactionEntity.title,
                    style = ReplacementTheme.typography.bodyLarge.copy(
                        color = Color.Black
                    )
                )
                Text(
                    dateFormatted,
                    style = ReplacementTheme.typography.bodyMedium.copy(
                        color = Gray
                    )
                )
            }
        }
        Text(
            text = textValue,
            style = ReplacementTheme.typography.bodySuperLarge.copy(
                fontSize = 18.sp,
                color = if (isIncome) LightGreen else Red
            )
        )
    }
}