package com.example.parcialdesarrollo2.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.parcialdesarrollo2.Modelo.Producto


class ProductoViewModel : ViewModel() {

    var productos by mutableStateOf(listOf<Producto>())
        private set

    var carrito by mutableStateOf(listOf<Producto>())
        private set

    val total: Double
        get() = carrito.sumOf { it.precio }

    fun agregarProducto(producto: Producto) {
        productos = productos + producto
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun agregarAlCarrito(producto: Producto) {
        carrito = carrito + producto
    }

    fun finalizarCompra(): String {
        val mensaje = if (carrito.isNotEmpty()) {
            "Compra realizada por $${total}"
        } else {
            "El carrito está vacío"
        }
        carrito = emptyList()
        return mensaje
    }
}