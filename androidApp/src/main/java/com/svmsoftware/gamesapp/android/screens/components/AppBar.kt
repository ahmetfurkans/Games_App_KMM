package com.svmsoftware.gamesapp.android.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Appbar(
    title: String
) {
    Row (
        modifier = Modifier.fillMaxWidth().background(Color.Black).padding(16.dp),
    ) {
        Text(text = title, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}
