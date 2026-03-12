package com.hilleliot.shop.data.repository

import com.hilleliot.shop.R
import com.hilleliot.shop.data.model.Product
import com.hilleliot.shop.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Luxury Bedding Set",
            description = "Premium 400-thread-count Egyptian cotton bedding set. Includes duvet cover, fitted sheet, and two pillowcases. Available in a range of colours to suit any bedroom.",
            category = ProductCategory.BEDROOM,
            price = 89.99,
            imageRes = R.drawable.product_1,
        ),
        Product(
            id = 2,
            title = "Ceramic Dinner Set",
            description = "Handcrafted ceramic dinner set for four. Includes plates, bowls, and mugs with a modern Nordic design. Dishwasher and microwave safe.",
            category = ProductCategory.KITCHEN,
            price = 64.99,
            imageRes = R.drawable.product_2,
        ),
        Product(
            id = 3,
            title = "Scented Candle Collection",
            description = "Set of three hand-poured soy wax candles with natural essential oils. Fragrances: Lavender & Vanilla, Fresh Linen, and Sea Breeze. Up to 40 hours burn time each.",
            category = ProductCategory.DECOR,
            price = 29.99,
            imageRes = R.drawable.product_3,
        ),
        Product(
            id = 4,
            title = "Bamboo Bath Towel Set",
            description = "Ultra-soft bamboo bath towels, set of four. Naturally antibacterial, hypoallergenic, and highly absorbent. Gentle on sensitive skin.",
            category = ProductCategory.BATHROOM,
            price = 44.99,
            imageRes = R.drawable.product_4,
        ),
        Product(
            id = 5,
            title = "Indoor Plant Pot Set",
            description = "Set of three terracotta plant pots with drainage holes and saucers. Perfect for succulents, herbs, and small indoor plants. Sizes: 10cm, 14cm, 18cm.",
            category = ProductCategory.DECOR,
            price = 24.99,
            imageRes = R.drawable.product_5,
        ),
        Product(
            id = 6,
            title = "Wooden Chopping Board",
            description = "Large solid acacia wood chopping board with juice groove. Pre-oiled for immediate use. Suitable for meat, vegetables, and bread. Size: 40 x 30 cm.",
            category = ProductCategory.KITCHEN,
            price = 34.99,
            imageRes = R.drawable.product_6,
        ),
        Product(
            id = 7,
            title = "Memory Foam Pillow",
            description = "Ergonomic memory foam pillow with cooling gel layer. Supports proper spinal alignment and reduces neck pain. Comes with a washable hypoallergenic cover.",
            category = ProductCategory.BEDROOM,
            price = 49.99,
            imageRes = R.drawable.product_7,
        ),
        Product(
            id = 8,
            title = "Garden Lantern Set",
            description = "Set of two solar-powered garden lanterns with warm LED lights. Automatic dusk-to-dawn operation. Weather-resistant and rust-proof. Height: 45cm.",
            category = ProductCategory.GARDEN,
            price = 39.99,
            imageRes = R.drawable.product_8,
        ),
        Product(
            id = 9,
            title = "Velvet Throw Blanket",
            description = "Sumptuous velvet throw blanket, 150 x 200 cm. Perfect for sofa or bed. Machine washable. Available in navy, sage green, and dusty rose.",
            category = ProductCategory.HOME_LIVING,
            price = 54.99,
            imageRes = R.drawable.product_9,
        ),
        Product(
            id = 10,
            title = "Bathroom Shelf Unit",
            description = "Freestanding bamboo bathroom shelf with three tiers. Water-resistant finish. Holds toiletries, towels, and plants. Easy to assemble. Size: 60 x 30 x 90 cm.",
            category = ProductCategory.BATHROOM,
            price = 59.99,
            imageRes = R.drawable.product_10,
        ),
        Product(
            id = 11,
            title = "Linen Cushion Covers (4-pack)",
            description = "Set of four natural linen cushion covers with invisible zip. Mix of textures and subtle patterns. Covers only, fits 45 x 45 cm inserts.",
            category = ProductCategory.HOME_LIVING,
            price = 32.99,
            imageRes = R.drawable.product_11,
        ),
        Product(
            id = 12,
            title = "Herb Garden Starter Kit",
            description = "Everything you need to grow your own herbs at home. Includes basil, parsley, coriander, and chives seeds, peat pots, compost, and growing guide.",
            category = ProductCategory.GARDEN,
            price = 19.99,
            imageRes = R.drawable.product_12,
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
