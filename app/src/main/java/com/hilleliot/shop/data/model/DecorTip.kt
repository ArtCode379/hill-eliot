package com.hilleliot.shop.data.model

data class DecorTip(val title: String, val body: String, val imageUrl: String)

val decorTips = listOf(
    DecorTip(
        "Layer Textures for Depth",
        "Combine wool, linen, and velvet cushions on your sofa for a luxurious layered look that adds warmth and visual interest. Mixing fabrics with different tactile qualities creates a rich sensory experience while making a space feel curated and lived-in. Try pairing a chunky knit throw with smooth silk cushions for maximum contrast.",
        "file:///android_asset/tip_1.jpg"
    ),
    DecorTip(
        "Bring the Outdoors In",
        "Houseplants purify air and add life to any room. Group plants of different heights for a natural, organic display. A tall fiddle-leaf fig anchors a corner, while trailing pothos cascades from a shelf and compact succulents fill the tabletops. Natural greenery softens hard edges and brings a calming, biophilic quality to your home.",
        "file:///android_asset/tip_2.jpg"
    ),
    DecorTip(
        "The Rule of Three",
        "Arrange décor items in odd numbers. Three candles, three vases, or three books create the most visually pleasing groupings. Our brains find asymmetric arrangements more dynamic and interesting than even numbers. Vary the height of objects within the grouping — tall, medium, and low — to draw the eye across the display in a natural arc.",
        "file:///android_asset/tip_3.jpg"
    ),
)
