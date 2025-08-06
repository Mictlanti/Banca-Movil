package com.horizon.bancamovil.view.mainViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.components.profileComponents.ComponentsProfile
import com.horizon.bancamovil.components.profileComponents.ProfileStyle
import com.horizon.bancamovil.data.AuthManager
import com.horizon.bancamovil.viewmodel.BankingViewModel

@Composable
fun ProfileViewRoute(
    navController: NavController,
    viewModel: BankingViewModel,
    authManager: AuthManager
) {

    val showSignOut = remember { mutableStateOf(false) }

    AlertSignOut(showSignOut.value, authManager, navController) {
        showSignOut.value = false
    }

    BasicOpeStyle(navController, viewModel, "Tu perfil") {
        item {
            ProfileStyle(authManager) {
                ComponentsProfile(authManager) {
                    showSignOut.value = true
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlertSignOut(signOut: Boolean, authManager: AuthManager, navController: NavController, onDismiss: () -> Unit) {

    val options = listOf("Volver", "Salir")

    if (signOut) {
        BasicAlertDialog(
            onDismiss,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                HeadLineLarge("¿Quieres salir de tu cuenta?")
                BodyMedium("Esta estará disponible cuando desees volver a ella")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.End),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEachIndexed { index, s ->
                        BodyMedium(
                            s,
                            modifier = Modifier
                                .clickable {
                                    if(index == 0) onDismiss() else authManager.singOut(navController)
                                }
                        )
                    }
                }
            }
        }
    }
}