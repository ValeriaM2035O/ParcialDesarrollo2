package com.example.parcialdesarrollo2.Imagen


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.request.ImageRequest
import coil.compose.AsyncImage

@Composable
fun ImagenProducto(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Imagen del producto",
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(3.dp, Color(0xFF3F51B5), RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}