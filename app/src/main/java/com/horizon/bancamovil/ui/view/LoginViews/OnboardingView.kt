package com.horizon.bancamovil.ui.view.LoginViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.horizon.bancamovil.R
import com.horizon.bancamovil.ui.components.loginComponents.BackgroundSplash
import com.horizon.bancamovil.ui.components.loginComponents.CountingPages
import com.horizon.bancamovil.ui.components.loginComponents.NextOrBackScreen
import com.horizon.bancamovil.ui.components.loginComponents.OneView
import com.horizon.bancamovil.ui.components.loginComponents.SignInViewGoogle
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingViewRoute(navController: NavController, authManager: AuthManager, bankingViewModel: BankingViewModel) {

    val pagerState = rememberPagerState(pageCount = { 5 })
    val painter = when(pagerState.currentPage) {
        0 -> R.drawable.ad_card_one
        1 -> R.drawable.ad_lady_shopping
        2 -> R.drawable.ad_card_two
        3 -> R.drawable.ad_men_shopping
        4 -> R.drawable.ad_liberty
        else -> R.drawable.ad_card_one
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { CountingPages(pagerState) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { pad ->
        Box {
            BackgroundSplash(painter)
            HorizontalPager(
                pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pad)
            ) { page ->
                when (page) {
                    0 -> OneView(
                        "Compra lo que necesitas, cuando lo necesitas",
                        "Tu cuenta bancaria siempre contigo. Controla tus gastos desde la app."
                    )
                    1 -> OneView(
                        "Estilo que combina con tu libertad financiera",
                        "Tu dinero, tu ritmo. Adminístralo desde una app que entiende tu estilo de vida."
                    )
                    2 -> OneView(
                        "Convierte cada gasto en una historia que vale la pena contar",
                        "Con nuestra tarjeta, cada compra puede ser una inversión en experiencias."
                    )
                    3 -> OneView(
                        "Da el siguiente paso hacia el futuro que mereces",
                        "Estatus no es lo que tienes, es cómo decides crecer. Hazlo con nosotros."
                    )
                    4 -> SignInViewGoogle(navController, authManager, bankingViewModel)
                }
            }
            NextOrBackScreen(
                pagerState,
                modifier = Modifier
                    .windowInsetsPadding(BottomAppBarDefaults.windowInsets)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}