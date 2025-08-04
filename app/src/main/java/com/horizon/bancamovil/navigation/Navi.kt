package com.horizon.bancamovil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.horizon.bancamovil.view.BalanceAndHistoryViewRoute
import com.horizon.bancamovil.view.BasicOpeBankingView.DepositViewRoute
import com.horizon.bancamovil.view.BasicOpeBankingView.TransferViewRoute
import com.horizon.bancamovil.view.WalletViewRoute

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = AppScreens.HomeView.route) {
        composable(AppScreens.HomeView.route) {
            WalletViewRoute(navController)
        }

        composable(AppScreens.BalanceAndHistoryView.route) {
            BalanceAndHistoryViewRoute(navController)
        }

        composable(AppScreens.DepositView.route) {
            DepositViewRoute(navController)
        }

        composable(AppScreens.TransferView.route) {
            TransferViewRoute(navController)
        }
    }

}