package org.akwapos.app.models

import androidx.compose.ui.text.TextStyle

data class ComposeTextModel(
    val text: String,
    val textStyle: TextStyle = TextStyle.Default,
    val onClick: (() -> Unit)? = null,
)