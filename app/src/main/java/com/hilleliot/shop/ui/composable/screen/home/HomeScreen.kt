package com.hilleliot.shop.ui.composable.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.hilleliot.shop.R
import com.hilleliot.shop.data.model.DecorTip
import com.hilleliot.shop.data.model.Product
import com.hilleliot.shop.data.model.decorTips
import com.hilleliot.shop.ui.composable.shared.DataBasedContainer
import com.hilleliot.shop.ui.state.DataUiState
import com.hilleliot.shop.ui.theme.HEDarkBlue
import com.hilleliot.shop.ui.theme.HELightBlue
import com.hilleliot.shop.ui.theme.HEPrimaryBlue
import com.hilleliot.shop.ui.theme.HESoftBlue
import com.hilleliot.shop.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (Int) -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToArticle: (Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {
        // Hero banner
        item {
            HeroBanner(onShopNowClick = onNavigateToProducts)
        }

        // Decor tips carousel
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Home Decor Inspiration",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(decorTips.size) { index ->
                    DecorTipCard(
                        tip = decorTips[index],
                        onClick = { onNavigateToArticle(index) },
                    )
                }
            }
        }

        // Featured products header
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Featured Products",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HEPrimaryBlue,
                    modifier = Modifier.clickable { onNavigateToProducts() },
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Featured products row
        item {
            DataBasedContainer(
                dataState = productsState,
                dataPopulated = { products ->
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(products.take(6)) { product ->
                            FeaturedProductCard(
                                product = product,
                                onCardClick = { onNavigateToProductDetails(product.id) },
                                onAddToCart = { viewModel.addToCart(product.id) },
                            )
                        }
                    }
                },
                dataEmpty = { },
            )
        }
    }
}

@Composable
private fun HeroBanner(onShopNowClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Brush.horizontalGradient(listOf(HEDarkBlue, HEPrimaryBlue))
            )
            .padding(24.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Column {
            Text(
                text = "Premium Home Goods",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.White,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Thoughtfully curated for modern living",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.85f),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onShopNowClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color.White,
                    contentColor = HEPrimaryBlue,
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = "Shop Now", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun DecorTipCard(tip: DecorTip, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column {
            AsyncImage(
                model = tip.imageUrl,
                contentDescription = tip.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.body,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun FeaturedProductCard(
    product: Product,
    onCardClick: () -> Unit,
    onAddToCart: () -> Unit,
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column {
            AsyncImage(
                model = "file:///android_asset/product_${product.id}.jpg",
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.category.displayName,
                    style = MaterialTheme.typography.bodySmall,
                    color = HEPrimaryBlue,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(R.string.price, product.price),
                    style = MaterialTheme.typography.titleSmall,
                    color = HEPrimaryBlue,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HEPrimaryBlue),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(vertical = 6.dp),
                ) {
                    Text(text = "Add to Cart", fontSize = 12.sp)
                }
            }
        }
    }
}
