package com.example.parcialdesarrollo2.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.parcialdesarrollo2.Imagen.ImagenProducto
import com.example.parcialdesarrollo2.ViewModel.ProductoViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.parcialdesarrollo2.Navegacion.Screens
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.parcialdesarrollo2.Estilos.PantallaConEstilo


@Composable
fun CatalogoProducto(viewModel: ProductoViewModel, navController: NavHostController) {
    PantallaConEstilo(
        titulo = "🛍 Catálogo de Productos",
        onBack = { }
    ) {
        LazyColumn {
            items(viewModel.productos) { producto ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screens.DetalleProducto.createRoute(producto.id))
                            }
                    ) {
                        ImagenProducto(producto.imagenUrl)
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(producto.nombre, fontSize = 16.sp)
                            Text("$${producto.precio}", fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            viewModel.agregarAlCarrito(producto)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Agregar al Carrito", color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total: $${viewModel.total}", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate(Screens.Registro.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Agregar Producto", color = Color.White)
            }

            Button(
                onClick = { navController.navigate(Screens.Carrito.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Ver Carrito", color = Color.White)
            }
        }
    }
}