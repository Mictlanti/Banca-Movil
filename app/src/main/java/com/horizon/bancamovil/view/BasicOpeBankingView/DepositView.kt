package com.horizon.bancamovil.view.BasicOpeBankingView

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.DataAccount
import com.horizon.bancamovil.components.BasicOpeComponents.DepositWithCard
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge

@Composable
fun DepositViewRoute(navController: NavController) {

    val depositWith = listOf(
        "Con efectivo en tiendas y bancos",
        "Con ingresos programados",
        "Con tu tarjeta de otro banco",
        "Con envíos de dinero del extranjero"
    )

    BasicOpeStyle(navController) {
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
                if (index == 2) "Trae dinero de otra cuenta todos los meses" else ""
            ) {

            }
        }
    }
}