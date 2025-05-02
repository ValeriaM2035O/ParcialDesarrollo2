package com.example.parcialdesarrollo2.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.parcialdesarrollo2.ViewModel.ProductoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.parcialdesarrollo2.Estilos.PantallaConEstilo
import com.example.parcialdesarrollo2.Imagen.ImagenProducto
import com.example.parcialdesarrollo2.Navegacion.Screens

@Composable
fun CarritoCompras(viewModel: ProductoViewModel, navController: NavHostController) {
    val context = LocalContext.current

    PantallaConEstilo(
        titulo = "🛒 Carrito de Compras",
        onBack = { navController.popBackStack() }
    ) {
        LazyColumn {
            items(viewModel.carrito) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImagenProducto(producto.imagenUrl)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(producto.nombre, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text("Precio: $${producto.precio}", fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val mensaje = viewModel.finalizarCompra()
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Finalizar Compra", color = Color.White)
            }

            Button(
                onClick = {
                    navController.navigate(Screens.Catalogo.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Volver al Catálogo", color = Color.White)
            }
        }
    }
}
