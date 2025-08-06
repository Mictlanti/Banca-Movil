package com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.DataAccount
import com.horizon.bancamovil.ui.components.BasicOpeComponents.DepositWithCard
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun DepositViewRoute(navController: NavController, viewModel: BankingViewModel) {

    val depositWith = listOf(
        "Con efectivo en tiendas y bancos",
        "Con ingresos programados",
        "Con tu tarjeta de otro banco",
        "Con envíos de dinero del extranjero"
    )

    BasicOpeStyle(navController, viewModel) {
        item {
            HeadLineLarge(
                "Datos de la cuenta",
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
        item {
            DataAccount()
        }
        item { Spacer(Modifier.height(10.dp)) }
        item {
            HeadLineLarge(
                "Ingresa dinero",
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
        itemsIndexed(depositWith) { index, s ->
            DepositWithCard(
                s,
                if (index == 1) "Trae dinero de otra cuenta todos los meses" else ""
            ) {
                when(index) {
                    0 -> navController.navigate(AppScreens.DepositInView.route)
                    1 -> navController.navigate(AppScreens.UnavailableView.route)
                    2 -> navController.navigate(AppScreens.DepositInOtherBankView.route)
                }
            }
        }
    }
}