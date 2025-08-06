package com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.SearchAndFavoritesSection
import com.horizon.bancamovil.ui.components.BasicOpeComponents.TransferOptionsCard
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun TransferViewRoute(navController: NavController, viewModel: BankingViewModel) {

    val optionsToTransfer = listOf(
        Icons.Default.FoodBank to "A una clave o tarjeta",
        Icons.Default.PersonPin to "A celular"
    )
    val commentList = listOf(
        "Cuentas bancarias o digitales",
        "Mercado Wallete o Dimo"
    )
    val state by viewModel.operationState.collectAsState()

    BasicOpeStyle(navController, viewModel, "Tranferir Biyuyo") {
        itemsIndexed(optionsToTransfer) { index, (vector, s) ->
            TransferOptionsCard(
                vector,
                s,
                commentList[index]
            ) {
                if (index == 0) navController.navigate(AppScreens.TransferKeyOrTarget.route) else navController.navigate(
                    AppScreens.ToCelView.route
                )
            }
        }
        item {
            SearchAndFavoritesSection(state)
        }
    }
}
