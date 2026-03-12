package com.hilleliot.shop.ui.composable.approot

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.hilleliot.shop.R
import com.hilleliot.shop.ui.composable.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentDestination: NavDestination?,
    isCartNotEmpty: Boolean,
    onClearCartIconClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val title = when {
        currentDestination?.hasRoute(NavRoute.Home::class) == true ->
            stringResource(R.string.top_bar_home_title)
        currentDestination?.hasRoute(NavRoute.Products::class) == true ->
            stringResource(R.string.top_bar_products_title)
        currentDestination?.hasRoute(NavRoute.Cart::class) == true ->
            stringResource(R.string.top_bar_cart_title)
        currentDestination?.hasRoute(NavRoute.Checkout::class) == true ->
            stringResource(R.string.top_bar_checkout_title)
        currentDestination?.hasRoute(NavRoute.Orders::class) == true ->
            stringResource(R.string.top_bar_orders_title)
        currentDestination?.hasRoute(NavRoute.Settings::class) == true ->
            stringResource(R.string.top_bar_settings_title)
        currentDestination?.hasRoute(NavRoute.ProductDetails::class) == true ->
            stringResource(R.string.top_bar_product_details_title)
        else -> stringResource(R.string.app_name)
    }

    val showBackButton = currentDestination?.hasRoute(NavRoute.ProductDetails::class) == true ||
            currentDestination?.hasRoute(NavRoute.Checkout::class) == true

    val showClearCart = currentDestination?.hasRoute(NavRoute.Cart::class) == true && isCartNotEmpty

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = {
            if (showClearCart) {
                IconButton(onClick = onClearCartIconClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Clear cart",
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        ),
    )
}
