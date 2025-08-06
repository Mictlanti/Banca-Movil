package com.horizon.bancamovil.view.BasicOpeBankingView.depositView

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
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.HiddenNumberInput
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.viewmodel.BankingViewModel

@Composable
fun DepositInOtherBankView(navController: NavController, viewModel: BankingViewModel) {

    val state by viewModel.operationState.collectAsState()
    val accountState by viewModel.accountState.collectAsState()

    BasicOpeStyle(
        navController,
        viewModel,
        bottomBar = {
            BottomAppBar {
                ElevatedButton(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BodyMedium("Continuar", color = MaterialTheme.colorScheme.onTertiaryContainer)
                }
            }
        }
    ) {
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