package com.example.parcialdesarrollo2.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parcialdesarrollo2.Screens.CarritoCompras
import com.example.parcialdesarrollo2.Screens.CatalogoProducto
import com.example.parcialdesarrollo2.Screens.DetalleProducto
import com.example.parcialdesarrollo2.Screens.RegistroProducto
import com.example.parcialdesarrollo2.ViewModel.ProductoViewModel

sealed class Screens(val route: String) {
    object Catalogo : Screens("catalogo")
    object Registro : Screens("registro")
    object Carrito : Screens("carrito")
    object DetalleProducto : Screens("detalle/{id}") {
        fun createRoute(id: Int) = "detalle/$id"
    }
}
@Composable
fun Navegacion(navController: NavHostController, viewModel: ProductoViewModel) {
    NavHost(navController = navController, startDestination = Screens.Catalogo.route) {
        composable(Screens.Catalogo.route) {
            CatalogoProducto(viewModel, navController)
        }
        composable(Screens.Carrito.route) {
            CarritoCompras(viewModel, navController)
        }
        composable(Screens.Registro.route) {
            RegistroProducto(viewModel, navController)
        }
        composable(Screens.DetalleProducto.route + "/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            if (productoId != null) {
                DetalleProducto(productoId, viewModel, navController)
            }
        }
    }
}