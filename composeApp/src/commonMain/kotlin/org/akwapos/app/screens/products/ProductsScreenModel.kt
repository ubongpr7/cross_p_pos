package org.akwapos.app.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductsScreenModel: ViewModel() {
    var searchProducts by mutableStateOf("")
}