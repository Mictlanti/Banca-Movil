package com.horizon.bancamovil.navigation

sealed class AppScreens(val route : String) {

    data object OnboardingScreen : AppScreens("OnboardingView")

    data object HomeView : AppScreens("HomeScreenRoute")

    data object ProfileView : AppScreens("ProfileViewRoute")

    data object UnavailableView : AppScreens("UnavailableView")

    data object BalanceAndHistoryView : AppScreens("BalanceHeaderView")

    data object DepositView : AppScreens("DepositView")

    data object DepositInView : AppScreens("DepositInView")

    data object DepositInOtherBankView : AppScreens("DepositInOtherBankView")

    data object TransferView : AppScreens("TransferView")

    data object ToCelView : AppScreens("ToCelView")

    data object WithdrawView : AppScreens("WithdrawView")

    data object YourKeyView : AppScreens("YourKeyView")

}