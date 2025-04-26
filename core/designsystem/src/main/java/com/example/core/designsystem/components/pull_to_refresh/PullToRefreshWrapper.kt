@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.core.designsystem.components.pull_to_refresh


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors


@Composable
fun PullToRefreshWrapper(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    pullToRefreshState: PullToRefreshState = rememberPullToRefreshState(),
    content: @Composable () -> Unit,
) {

    PullToRefreshBox(

        modifier = modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = AppColors.secondaryContainer,
                color = AppColors.onSecondaryContainer,
                state = pullToRefreshState
            )
        }
    ) {
       content()
    }

}