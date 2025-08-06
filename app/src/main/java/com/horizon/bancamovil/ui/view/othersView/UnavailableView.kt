package com.horizon.bancamovil.ui.view.othersView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun UnavailableView(navController: NavController, viewModel: BankingViewModel) {
    BasicOpeStyle(navController, viewModel) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
            ){
                Icon(
                    Icons.Default.Warning,
                    "unavailable",
                    modifier = Modifier.size(200.dp)
                )
                HeadLineLarge("Sección no disponible")
                Spacer(Modifier.height(20.dp))
                BodyLarge("Esta sección aún está deshabilitada")
                BodyLarge("Estamos trabajando para que muy pronto disfutes de ella")
                Spacer(Modifier.height(20.dp))
                BodyLarge("Agradecemos tu paciencia")
            }
        }
    }
}