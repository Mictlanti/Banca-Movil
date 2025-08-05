package com.horizon.bancamovil.view.BasicOpeBankingView.ExtraWithdraw

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.horizon.bancamovil.R
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.CardSkeleton
import com.horizon.bancamovil.components.BasicOpeComponents.HiddenNumberInput
import com.horizon.bancamovil.components.BasicOpeComponents.WithDrawBottomAppBar
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge

@Composable
fun WithdrawViewRoute(navController: NavController) {

    val listSites = listOf(
        "Mercado Pago Express" to "Consulta el monto en el local",
        "7-Eleven" to "Hasta $2,000 por retiro",
        "Circulo K-Extra" to "Hasta $1,000 por retiro",
        "Soriana" to "Hasta $2,000 por retiro",
        "Chedraui" to "Hasta $10,000 por retiro",
        "Bodega Aurrera" to "Hasta $3,000 por retiro",
        "Walmart" to "Hasta $3,000 por retiro"
    )
    val listImage = listOf(
        R.drawable.mercado_pago_logo,
        R.drawable.seven_eleven_logo,
        R.drawable.circulo_k_logo,
        R.drawable.soriana_logo,
        R.drawable.chedraui_logo,
        R.drawable.aurrera_logo,
        R.drawable.logo_walmart_vector,
    )

    BasicOpeStyle(
        navController,
        bottomBar = {
            WithDrawBottomAppBar(true, true)
        }
    ) {
        item {
            HeadLineLarge("Monto a retirar")
        }
        item {
            HiddenNumberInput()
        }
        item {
            HeadLineLarge("Elige el lugar de retiro")
        }
        itemsIndexed(listSites) { index, (site, amount) ->
            CardSkeleton(
                site,
                amount,
                listImage[index],
                cost = if (index == 5 || index == 6) "Costo: $25" else "Gratis",
                free = !(index == 5 || index == 6)
            )
        }
    }
}
