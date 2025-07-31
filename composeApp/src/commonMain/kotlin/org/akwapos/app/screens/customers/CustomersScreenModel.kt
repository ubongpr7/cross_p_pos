package org.akwapos.app.screens.customers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class CustomersScreenModel: ScreenModel {
    var searchCustomers by mutableStateOf("")
}