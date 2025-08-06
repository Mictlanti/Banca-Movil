package com.horizon.bancamovil.ui.view.BasicOpeBankingView.yourKey

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.SectionDataUser
import com.horizon.bancamovil.ui.components.BasicOpeComponents.SharedDataUserForeign
import com.horizon.bancamovil.ui.components.BasicOpeComponents.SharedDataUserKey
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.domain.state.DataUser
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel
import kotlinx.coroutines.launch

@Composable
fun YourKeyRoute(
    navController: NavController,
    viewModel: BankingViewModel,
    authManager: AuthManager
) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()
    val nextPage = (pagerState.currentPage + 1).coerceIn(0, pagerState.pageCount - 1)
    val lastPage = (pagerState.currentPage - 1).coerceIn(0, pagerState.pageCount - 1)
    val accountState by viewModel.accountState.collectAsState()

    BasicOpeStyle(navController, viewModel) {
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

        if (accountState.phoneNumber == null && accountState.neighborhood == null && accountState.district == null && accountState.name == null) {
            item {
                BodyMedium(
                    "Datos incompletos",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        item {
            HorizontalOptionsData(pagerState, authManager, accountState)
        }
    }
}


@Composable
fun HorizontalOptionsData(
    pagerState: PagerState,
    authManager: AuthManager,
    accountState: DataUser
) {

    val nameUser = authManager.getCurrentUser()?.displayName ?: "-"
    val emailUser = authManager.getCurrentUser()?.email ?: "-"
    val address = "${accountState.district ?: "-"} / ${accountState.neighborhood ?: "-"}"
    val keyUser =
        if (accountState.phoneNumber != null && accountState.district != null && accountState.neighborhood != null && accountState.name != null) {
            "55874526598642"
        } else "-"

    val listDataNational = listOf(
        "Clave" to keyUser,
        "Beneficiario" to nameUser,
        "Instituto" to "Mimatika"
    )
    val listDataForeign = listOf(
        "Beneficiario" to nameUser,
        "Celular" to (accountState.phoneNumber ?: "-").toString(),
        "e-mail" to emailUser,
        "Ciudad / Estado" to address
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when (page) {
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
                                .clickable { },
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            ),
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
                                .clickable { },
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            ),
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