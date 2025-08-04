package com.horizon.bancamovil.view

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.HomeComponents.AccountBalanceSection
import com.horizon.bancamovil.components.HomeComponents.BasicOperationBanking
import com.horizon.bancamovil.components.HomeComponents.CreditsStyle
import com.horizon.bancamovil.components.HomeComponents.HomeBottomBar
import com.horizon.bancamovil.components.HomeComponents.PayServicesMenu
import com.horizon.bancamovil.components.HomeComponents.SectionCard
import com.horizon.bancamovil.components.HomeComponents.TopBarHome
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.navigation.AppScreens

@Composable
fun WalletViewRoute(navController: NavController) {

    val backgroundGradientColors = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.background
    )

    Scaffold(
        topBar = {
            TopBarHome()
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
                BalanceCard(navController)
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
private fun BalanceCard(navController: NavController) {
    SectionCard {
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxSize()
        ) {
            AccountBalanceSection { navController.navigate(AppScreens.BalanceAndHistoryView.route) }
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


