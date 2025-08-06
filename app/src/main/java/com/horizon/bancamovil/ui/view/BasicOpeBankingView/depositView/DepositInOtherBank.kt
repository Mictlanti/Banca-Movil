package com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.HiddenNumberInput
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun DepositInOtherBankView(navController: NavController, viewModel: BankingViewModel) {

    val state by viewModel.operationState.collectAsState()
    val accountState by viewModel.accountState.collectAsState()
    val code = if (state.rawText.isEmpty()) 0.0 else state.rawText.toDouble()

    BasicOpeStyle(
        navController,
        viewModel,
        bottomBar = {
            BottomAppBar {
                ElevatedButton(
                    onClick = {
                        if (viewModel.operationsAvailable() && accountState.valueAccount >= code) {
                            viewModel.events(BankingEvents.DepositInEvent((code / 105) * 100))
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    enabled = viewModel.operationsAvailable() && accountState.valueAccount >= code,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BodyMedium("Continuar", color = MaterialTheme.colorScheme.onTertiaryContainer)
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
            HeadLineLarge("Ingresa el monto")
        }
        item {
            BodyLarge("Se cobrabá un 5% de comisión")
        }
        item {
            HiddenNumberInput(
                state,
                viewModel,
                accountState,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                false,
                "El límite de ingreso es de $9,000",
                false
            )
        }
    }
}