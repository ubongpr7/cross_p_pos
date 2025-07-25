package org.akwapos.app.screens.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransactionsScreenModel: ViewModel() {
    var searchTransactions by mutableStateOf("")
}