package com.horizon.bancamovil.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BalancerTopAppBar
import com.horizon.bancamovil.ui.components.HistoryMovements
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.MoneyAvailable
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.theme.abelFont
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun BalanceAndHistoryViewRoute(navController: NavController, viewModel: BankingViewModel) {

    val backgroundGradientColors = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.background
    )

    Scaffold(
        topBar = {
            BalancerTopAppBar(navController, viewModel)
        },
        modifier = Modifier.fillMaxSize()
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .background(brush = Brush.verticalGradient(backgroundGradientColors)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                SectionCardHeader(viewModel)
            }
            item {
                SectionCard(1f) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        BodyLarge("Tus gastos")
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .2f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionCardHeader(viewModel: BankingViewModel) {
    SectionCard(.7f) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MoneyAvailable()
            HistoryMovements(viewModel)
        }
    }
}