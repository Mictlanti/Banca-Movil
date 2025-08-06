package com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraWithdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.domain.state.DataUser
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.CardSkeleton
import com.horizon.bancamovil.ui.components.BasicOpeComponents.HiddenNumberInput
import com.horizon.bancamovil.ui.components.BasicOpeComponents.WithDrawBottomAppBar
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView.depositSites
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun WithdrawViewRoute(navController: NavController, viewModel: BankingViewModel) {

    val state by viewModel.operationState.collectAsState()
    val accountState by viewModel.accountState.collectAsState()
    val indexSelected = rememberSaveable { mutableStateOf(0) }
    val code = if (state.rawText.isEmpty()) 0.0 else state.rawText.toDouble()
    val selectedSite = depositSites.getOrNull(indexSelected.value)
    val color = selectedSite?.let {
        if (code in 20.0..it.maxAmount && code <= accountState.valueAccount) {
            MaterialTheme.colorScheme.tertiaryContainer
        }
        else {
            MaterialTheme.colorScheme.errorContainer
        }
    } ?: MaterialTheme.colorScheme.tertiaryContainer
    val showAlert = remember { mutableStateOf(false) }

    WithdrawalAlert(
        showAlert.value,
        navController,
        viewModel,
        code,
        if (indexSelected.value == 5 || indexSelected.value == 6) 25 else 0,
        accountState
    ) {
        showAlert.value = false
    }

    BasicOpeStyle(
        navController,
        viewModel,
        bottomBar = {
            WithDrawBottomAppBar(
                state,
                selectedSite,
                enabled = viewModel.operationsAvailable() && accountState.valueAccount >= code
            ) {
                when (indexSelected.value) {
                    5, 6 -> {
                        showAlert.value = true
                    }

                    else -> {
                        if(viewModel.operationsAvailable() && code <= accountState.valueAccount){
                            viewModel.events(BankingEvents.WithdrawEvent(code))
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
            HeadLineLarge("Monto a retirar")
        }
        item {
            HiddenNumberInput(state, viewModel, accountState, color)
        }
        item {
            HeadLineLarge("Elige el lugar de retiro")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawalAlert(
    showAlert: Boolean,
    navController: NavController,
    viewModel: BankingViewModel,
    code: Double,
    commission: Int,
    data: DataUser,
    isWithdrawal: Boolean = true,
    onDismiss: () -> Unit
) {

    val terms = listOf("Mi cuenta", "En Sucursal")
    val commit = if (isWithdrawal) "retiro" else "ingreso"

    fun condition(index: Int) {
        if (index == 0) {
            if(viewModel.operationsAvailable()) {
                if (isWithdrawal && data.valueAccount >= code - commission) {
                    viewModel.events(BankingEvents.DepositInEvent(code - commission))
                } else {
                    viewModel.events(
                        BankingEvents.WithdrawEvent(code + commission)
                    )
                }
            }
        } else {
            viewModel.events(BankingEvents.WithdrawEvent(code))
        }
    }

    if (showAlert) {
        BasicAlertDialog(
            onDismiss,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
            ) {
                BodyLarge(
                    "Esta opción cobra una comisión por $commit",
                    color = MaterialTheme.colorScheme.onSecondary
                )
                BodyMedium("Elige la forma de pago", color = MaterialTheme.colorScheme.onSecondary)
                Row(modifier = Modifier.fillMaxWidth()) {
                    terms.forEachIndexed { index, s ->
                        Button(
                            onClick = {
                                condition(index)
                                //Navegar a una vista de recibo de retiro
                                navController.navigate(AppScreens.HomeView.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            BodyMedium(s, color = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                    }
                }
            }
        }
    }
}
