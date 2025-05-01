package com.example.parcialdesarrollo2.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.parcialdesarrollo2.Imagen.ImagenProducto
import com.example.parcialdesarrollo2.ViewModel.ProductoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.parcialdesarrollo2.Estilos.PantallaConEstilo

@Composable
fun DetalleProducto(id: Int, viewModel: ProductoViewModel, navController: NavHostController) {
    val producto = viewModel.obtenerProductoPorId(id)
    val context = LocalContext.current

    if (producto == null) {
        PantallaConEstilo(titulo = "❌ Producto no encontrado", onBack = { navController.popBackStack() }) {
            Text("El producto con ID $id no existe.")
        }
    } else {
        PantallaConEstilo(titulo = "🔍 Detalle del Producto", onBack = { navController.popBackStack() }) {
            Column {
                ImagenProducto(producto.imagenUrl)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Nombre: ${producto.nombre}", fontSize = 16.sp)
                Text("Precio: $${producto.precio}", fontSize = 16.sp)
                Text("Descripción: ${producto.descripcion}", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.agregarAlCarrito(producto)
                        Toast.makeText(context, "${producto.nombre} agregado al carrito", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar al Carrito", color = Color.White)
                }
            }
        }
    }
}