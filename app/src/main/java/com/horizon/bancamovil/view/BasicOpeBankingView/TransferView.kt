package com.horizon.bancamovil.view.BasicOpeBankingView

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.SearchAndFavoritesSection
import com.horizon.bancamovil.components.BasicOpeComponents.TransferOptionsCard

@Composable
fun TransferViewRoute(navController: NavController) {

    val optionsToTransfer = listOf(
        Icons.Default.FoodBank to "A una clave o tarjeta",
        Icons.Default.PersonPin to "A celular"
    )
    val commentList = listOf(
        "Cuentas bancarias o digitales",
        "Mercado Wallete o Dimo"
    )

    BasicOpeStyle(navController, "Tranferir Biyuyo") {
        itemsIndexed(optionsToTransfer) { index, (vector, s) ->
            TransferOptionsCard(
                vector,
                s,
                commentList[index]
            )
        }
        item {
            SearchAndFavoritesSection()
        }
    }
}
