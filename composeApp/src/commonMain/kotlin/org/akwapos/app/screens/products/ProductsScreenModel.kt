package org.akwapos.app.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class ProductsScreenModel: ScreenModel {
    var searchProducts by mutableStateOf("")
}