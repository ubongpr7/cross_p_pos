package org.akwapos.app.screens.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class TransactionsScreenModel: ScreenModel {
    var searchTransactions by mutableStateOf("")
}