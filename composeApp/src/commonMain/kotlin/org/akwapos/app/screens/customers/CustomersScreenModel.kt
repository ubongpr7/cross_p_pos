package org.akwapos.app.screens.customers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CustomersScreenModel: ViewModel() {
    var searchCustomers by mutableStateOf("")
}