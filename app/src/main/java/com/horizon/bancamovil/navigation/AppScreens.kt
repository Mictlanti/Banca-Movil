package com.horizon.bancamovil.navigation

sealed class AppScreens(val route : String) {

    data object HomeView : AppScreens("HomeScreenRoute")

    data object BalanceAndHistoryView : AppScreens("BalanceHeaderView")

    data object DepositView : AppScreens("DepositView")

    data object TransferView : AppScreens("TransferView")

}