package org.akwapos.app.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.akwapos.app.core.ProductClient

class ProductsScreenModel : ScreenModel {
    val product = ProductClient.getProductFlow()
        .stateIn(scope = screenModelScope, started = SharingStarted.WhileSubscribed(5000), null)

    var searchProducts by mutableStateOf("")

}