package org.akwapos.app.screens.pointofsale

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PointOfSaleScreenModel: ViewModel() {
    var searchProduct by mutableStateOf("")
    var displayCurrentSaleMobile by mutableStateOf(false)
    var customer by mutableStateOf("")
    var showCustomerPopup by mutableStateOf(false)
    var table by mutableStateOf("")
    var showTablePopup by mutableStateOf(false)
}