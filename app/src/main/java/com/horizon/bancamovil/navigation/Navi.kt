package com.horizon.bancamovil.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.ui.view.BalanceAndHistoryViewRoute
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView.DepositViewRoute
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer.ToCelViewOne
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraWithdraw.WithdrawViewRoute
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer.TransferViewRoute
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView.DepositInOtherBankView
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.depositView.DepositInView
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer.KeyOrTargetViewOne
import com.horizon.bancamovil.ui.view.BasicOpeBankingView.yourKey.YourKeyRoute
import com.horizon.bancamovil.ui.view.LoginViews.OnboardingViewRoute
import com.horizon.bancamovil.ui.view.othersView.UnavailableView
import com.horizon.bancamovil.ui.view.mainViews.profileViews.ProfileViewRoute
import com.horizon.bancamovil.ui.view.mainViews.WalletViewRoute
import com.horizon.bancamovil.ui.view.mainViews.profileViews.AccountDetailsRoute
import com.horizon.bancamovil.ui.view.othersView.AboutViewRoute
import com.horizon.bancamovil.ui.view.othersView.ContactMeRoute
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun Navigation(viewModel: BankingViewModel, context: Context) {

    val navController = rememberNavController()
    val authManager = AuthManager(context)
    val user: FirebaseUser? = authManager.getCurrentUser()

    NavHost(
        navController,
        startDestination = if (user != null) AppScreens.HomeView.route else AppScreens.OnboardingScreen.route
    ) {
        composable(AppScreens.OnboardingScreen.route) {
            OnboardingViewRoute(navController, authManager, viewModel)
        }

        composable(AppScreens.HomeView.route) {
            WalletViewRoute(navController, viewModel, authManager)
        }

        composable(AppScreens.ProfileView.route) {
            ProfileViewRoute(navController, viewModel, authManager)
        }

        composable(AppScreens.DetailsView.route) {
            AccountDetailsRoute(navController, viewModel)
        }

        composable(AppScreens.UnavailableView.route) {
            UnavailableView(navController, viewModel)
        }

        composable(AppScreens.BalanceAndHistoryView.route) {
            BalanceAndHistoryViewRoute(navController, viewModel)
        }

        composable(AppScreens.DepositView.route) {
            DepositViewRoute(navController, viewModel)
        }

        composable(AppScreens.DepositInView.route) {
            DepositInView(navController, viewModel)
        }

        composable(AppScreens.DepositInOtherBankView.route) {
            DepositInOtherBankView(navController, viewModel)
        }

        composable(AppScreens.TransferView.route) {
            TransferViewRoute(navController, viewModel)
        }

        composable(AppScreens.TransferKeyOrTarget.route) {
            KeyOrTargetViewOne(navController, viewModel)
        }

        composable(AppScreens.ToCelView.route) {
            ToCelViewOne(navController, viewModel)
        }

        composable(AppScreens.WithdrawView.route) {
            WithdrawViewRoute(navController, viewModel)
        }

        composable(AppScreens.YourKeyView.route) {
            YourKeyRoute(navController, viewModel, authManager)
        }

        composable(AppScreens.AboutView.route) {
            AboutViewRoute(navController, viewModel)
        }

        composable(AppScreens.ContactMeView.route) {
            ContactMeRoute(navController, viewModel)
        }
    }

}