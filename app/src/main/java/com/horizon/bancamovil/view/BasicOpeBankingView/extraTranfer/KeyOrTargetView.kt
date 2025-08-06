package com.horizon.bancamovil.view.BasicOpeBankingView.extraTranfer

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.HorizontalDetailsAccount
import com.horizon.bancamovil.components.BasicOpeComponents.TransferCard
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.events.BankingEvents
import com.horizon.bancamovil.viewmodel.BankingViewModel

@Composable
fun KeyOrTargetViewOne(navController: NavController, viewModel: BankingViewModel) {

    val keyTarget = rememberSaveable { mutableStateOf("") }
    val titularTarget = rememberSaveable { mutableStateOf("") }
    val institutionBank = rememberSaveable { mutableStateOf("") }
    val pagerState = rememberPagerState(pageCount = { 4 })
    val state by viewModel.operationState.collectAsState()

    BasicOpeStyle(navController, viewModel) {
        item { HeadLineLarge("Ingresa el monto") }
        item {
            TransferCard(
                pagerState,
                state,
                viewModel,
                titularTarget.value,
                keyTarget.value,
                institutionBank.value,
                MaterialTheme.colorScheme.onTertiary
            )
        }
        item {
            HorizontalDetailsAccount(
                pagerState,
                keyTarget,
                titularTarget,
                institutionBank
            ) {
                viewModel.transferCurrently(
                    keyTarget.value.toIntOrNull() ?: 0,
                    titularTarget.value,
                    institutionBank.value
                    )
                navController.popBackStack()
                viewModel.events(BankingEvents.DepositInEvent(state.rawText.toDouble()))
            }
        }
    }
}