package org.akwapos.app.screens.pointofsale

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.akwapos.app.core.ProductClient

class PointOfSaleScreenModel: ScreenModel {

    val product = ProductClient.getProductFlow()
        .stateIn(scope = screenModelScope, started = SharingStarted.WhileSubscribed(5000), null)

    var searchProduct by mutableStateOf("")
    var displayCurrentSaleMobile by mutableStateOf(false)
    var customer by mutableStateOf("")
    var showCustomerPopup by mutableStateOf(false)
    var table by mutableStateOf("")
    var showTablePopup by mutableStateOf(false)
}