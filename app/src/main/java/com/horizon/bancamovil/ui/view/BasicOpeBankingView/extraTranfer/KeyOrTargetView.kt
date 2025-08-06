package com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.HorizontalDetailsAccount
import com.horizon.bancamovil.ui.components.BasicOpeComponents.TransferCard
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun KeyOrTargetViewOne(navController: NavController, viewModel: BankingViewModel) {

    val keyTarget = rememberSaveable { mutableStateOf("") }
    val titularTarget = rememberSaveable { mutableStateOf("") }
    val institutionBank = rememberSaveable { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { 4 })
    val state by viewModel.operationState.collectAsState()
    val account by viewModel.accountState.collectAsState()
    val code = if (state.rawText.isEmpty()) 0.0 else state.rawText.toDouble()

    BasicOpeStyle(navController, viewModel) {
        if(!viewModel.operationsAvailable()) {
            item {
                BodySmall("Datos no completados", color = MaterialTheme.colorScheme.error)
            }
        }
        item { HeadLineLarge("Ingresa el monto") }
        item {
            Column {
                BodySmall("Disponible: $ ${viewModel.formatCurrency(account.valueAccount)}")
                TransferCard(
                    pagerState,
                    state,
                    viewModel,
                    titularTarget.value,
                    keyTarget.value,
                    institutionBank.value,
                    MaterialTheme.colorScheme.onTertiary,
                    pagerState.currentPage >= 2
                )
            }
        }
        item {
            HorizontalDetailsAccount(
                pagerState,
                keyTarget,
                titularTarget,
                institutionBank,
                viewModel,
                code.toInt() in 1..account.valueAccount.toInt(),
            ) {
                viewModel.transferCurrently(
                    keyTarget.value.toIntOrNull() ?: 0,
                    titularTarget.value,
                    institutionBank.value
                    )
                navController.popBackStack()
                viewModel.events(BankingEvents.WithdrawEvent(state.rawText.toDouble()))
            }
        }
    }
}