package com.horizon.bancamovil.view.BasicOpeBankingView.YourKey

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.BasicOpeComponents.SectionDataUser
import com.horizon.bancamovil.components.BasicOpeComponents.SharedDataUserForeign
import com.horizon.bancamovil.components.BasicOpeComponents.SharedDataUserKey
import com.horizon.bancamovil.components.HomeComponents.SectionCard
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import kotlinx.coroutines.launch

@Composable
fun YourKeyRoute(navController: NavController) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()
    val nextPage = (pagerState.currentPage + 1).coerceIn(0, pagerState.pageCount - 1)
    val lastPage = (pagerState.currentPage - 1).coerceIn(0, pagerState.pageCount - 1)

    BasicOpeStyle(navController) {
        item {
            HeadLineLarge("Comporte tus datos y recibe el dinero")
        }

        item {
            SectionDataUser(
                pagerState.currentPage == 1,
                nextPage = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            lastPage
                        )
                    }
                }
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(
                        nextPage
                    )
                }
            }
        }

        item {
            HorizontalOptionsData(pagerState)
        }
    }
}


@Composable
fun HorizontalOptionsData(pagerState: PagerState) {

    val listDataNational = listOf(
        "Clave" to "55784851459862",
        "Beneficiario" to "Lolita Cortéz Casanova Elisey",
        "Instituto" to "Banco de México"
    )
    val listDataForeign = listOf(
        "Beneficiario" to "Lolita Cortéz Casanova Elisey",
        "Celular" to "5248751543",
        "e-mail" to "missLoli.casanova@machupichu.com",
        "Ciudad / Estado" to "XochiYork, Mexico City"
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when(page) {
            0 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    SectionCard {
                        SharedDataUserKey(listDataNational)
                    }
                    SectionCard(5f) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .clickable {  },
                            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Share,
                                "Shared Data",
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                            BodyLarge("Compartir datos", color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }
            }

            1 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    SectionCard {
                        SharedDataUserForeign(listDataForeign)
                    }
                    SectionCard(5f) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .clickable {  },
                            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Share,
                                "Shared Data",
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                            BodyLarge("Compartir datos", color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }
            }
        }
    }

}