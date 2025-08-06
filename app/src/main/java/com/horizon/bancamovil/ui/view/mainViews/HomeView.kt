package com.horizon.bancamovil.ui.view.mainViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.HomeComponents.AccountBalanceSection
import com.horizon.bancamovil.ui.components.HomeComponents.BasicOperationBanking
import com.horizon.bancamovil.ui.components.HomeComponents.CreditsStyle
import com.horizon.bancamovil.ui.components.HomeComponents.HomeBottomBar
import com.horizon.bancamovil.ui.components.HomeComponents.PayServicesMenu
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.HomeComponents.TopBarHome
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.domain.state.DataUser
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun WalletViewRoute(navController: NavController, viewModel: BankingViewModel, authManager: AuthManager) {

    val backgroundGradientColors = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.background
    )
    val accountState by viewModel.accountState.collectAsState()

    Scaffold(
        topBar = {
            TopBarHome(navController, authManager)
        },
        bottomBar = {
            HomeBottomBar()
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .background(brush = Brush.verticalGradient(backgroundGradientColors)),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                BalanceCard(navController, viewModel, accountState)
            }
            item {
                CreditCardSummary()
            }
            item {
                SectionCard {
                    PayServicesMenu()
                }
            }
            item {
                BodyLarge(
                    "Favoritos",
                    color = MaterialTheme.colorScheme.background.copy(alpha = .4f),
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
    }
}

@Composable
private fun BalanceCard(navController: NavController, viewModel: BankingViewModel, accountState: DataUser) {
    SectionCard {
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxSize()
        ) {
            AccountBalanceSection(viewModel, accountState) { navController.navigate(AppScreens.BalanceAndHistoryView.route) }
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = .1f
                )
            )
            BasicOperationBanking(navController)
        }
    }
}

@Composable
fun CreditCardSummary() {
    SectionCard(3f) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            BodyLarge("Créditos")
            CreditsStyle("$ 2,500")
        }
    }
}


