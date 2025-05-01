package com.example.parcialdesarrollo2.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.parcialdesarrollo2.Modelo.Producto
import com.example.parcialdesarrollo2.ViewModel.ProductoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.parcialdesarrollo2.Estilos.PantallaConEstilo

@Composable
fun RegistroProducto(viewModel: ProductoViewModel, navController: NavHostController) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    PantallaConEstilo(
        titulo = "📝 Registro de Producto",
        onBack = { navController.popBackStack() }
    ) {
        Column {
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
            OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
            OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("Imagen URL") })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank() && descripcion.isNotBlank() && imagenUrl.isNotBlank()) {
                        viewModel.agregarProducto(
                            Producto(
                                id = viewModel.productos.size + 1,
                                nombre = nombre,
                                precio = precio.toDoubleOrNull() ?: 0.0,
                                descripcion = descripcion,
                                imagenUrl = imagenUrl
                            )
                        )
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar", color = Color.White)
            }
        }
    }
}