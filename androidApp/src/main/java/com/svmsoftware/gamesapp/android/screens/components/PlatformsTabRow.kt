package com.svmsoftware.gamesapp.android.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.svmsoftware.gamesapp.domain.model.Platform

@Composable
fun PlatformsTabRow(
    platforms: List<Platform>,
    selectedPlatformIndex: Int,
    onSelectTab: (Int) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedPlatformIndex,
        edgePadding = 0.dp,
        containerColor = Color.Transparent,
        // contentColor = Color(0xFF35323A),
        indicator = { tabPositions ->
            if (selectedPlatformIndex != -1) {
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedPlatformIndex])
                        .fillMaxWidth()
                )
            }
        }
    ) {
        platforms.forEachIndexed { index, tab ->
            Tab(
                selected = selectedPlatformIndex == index,
                onClick = {
                    if (selectedPlatformIndex == index) {
                        onSelectTab(-1)
                    } else {
                        onSelectTab(index)
                    }
                },
                modifier = Modifier.padding(8.dp),
                content = {
                    Text(
                        text = tab.name,
                        modifier = Modifier
                            .padding(8.dp),
                        color = if (selectedPlatformIndex == index) MaterialTheme.colorScheme.tertiary else Color(
                            0xFF35323A
                        )
                    )
                }
            )
        }
    }
}