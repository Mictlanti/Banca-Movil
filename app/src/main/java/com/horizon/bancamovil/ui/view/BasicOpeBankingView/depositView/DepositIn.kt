package com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.horizon.bancamovil.R
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.CardSkeleton
import com.horizon.bancamovil.ui.components.BasicOpeComponents.HiddenNumberInput
import com.horizon.bancamovil.ui.components.BasicOpeComponents.WithDrawBottomAppBar
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.domain.model.DepositSite
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraWithdraw.WithdrawalAlert
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

val depositSites = listOf(
    DepositSite(
        "Mercado Pago Express",
        "Consulta el monto en el local",
        R.drawable.mercado_pago_logo,
        Double.MAX_VALUE
    ),
    DepositSite("7-Eleven", "Hasta $2,000 por depósito", R.drawable.seven_eleven_logo, 2000.0),
    DepositSite("Circulo K-Extra", "Hasta $1,000 por depósito", R.drawable.circulo_k_logo, 1000.0),
    DepositSite("Soriana", "Hasta $2,000 por depósito", R.drawable.soriana_logo, 2000.0),
    DepositSite("Chedraui", "Hasta $10,000 por depósito", R.drawable.chedraui_logo, 10000.0),
    DepositSite(
        "Bodega Aurrera",
        "Hasta $3,000 por depósito",
        R.drawable.aurrera_logo,
        3000.0,
        "Costo: $25",
        false
    ),
    DepositSite(
        "Walmart",
        "Hasta $3,000 por depósito",
        R.drawable.logo_walmart_vector,
        3000.0,
        "Costo: $25",
        false
    )
)

@Composable
fun DepositInView(navController: NavController, viewModel: BankingViewModel) {

    val state by viewModel.operationState.collectAsState()
    val accountState by viewModel.accountState.collectAsState()
    val indexSelected = rememberSaveable { mutableStateOf(0) }
    val code = if (state.rawText.isEmpty()) 0.0 else state.rawText.toDouble()
    val selectedSite = depositSites.getOrNull(indexSelected.value)
    val color = selectedSite?.let {
        if (code in 20.0..it.maxAmount) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.errorContainer
    } ?: MaterialTheme.colorScheme.tertiaryContainer
    val showAlert = remember { mutableStateOf(false) }

    WithdrawalAlert(
        showAlert.value,
        navController,
        viewModel,
        code,
        if (indexSelected.value == 5 || indexSelected.value == 6) 25 else 0,
        accountState,
        false
    ) {
        showAlert.value = false
    }

    BasicOpeStyle(
        navController,
        viewModel,
        bottomBar = {
            WithDrawBottomAppBar(state, selectedSite, viewModel.operationsAvailable()) {
                when(indexSelected.value) {
                    5, 6 -> {
                        showAlert.value = true
                    }
                    else -> {
                        if(viewModel.operationsAvailable()) {
                            viewModel.events(BankingEvents.DepositInEvent(code))
                            //Navegar a una vista de recibo de retiro
                            navController.navigate(AppScreens.HomeView.route)
                        }
                    }
                }
            }
        }
    ) {
        if(!viewModel.operationsAvailable()) {
            item {
                BodySmall("Datos no completados", color = MaterialTheme.colorScheme.error)
            }
        }
        item {
            HeadLineLarge("Monto a depositar")
        }
        item {
            HiddenNumberInput(
                state,
                viewModel,
                accountState,
                message = "Cantidad mínima $20",
                color = color
            )
        }
        item {
            HeadLineLarge("Elige el lugar")
        }
        itemsIndexed(depositSites) { index, site ->
            CardSkeleton(
                site = site.name,
                amount = site.note,
                imageVector = site.imageRes,
                cost = site.cost,
                free = site.isFree,
                index = index,
                state = state,
                viewModel = viewModel
            ) {
                indexSelected.value = index
                viewModel.events(BankingEvents.AvailableSitesDeposit(index))
            }
        }
    }
}