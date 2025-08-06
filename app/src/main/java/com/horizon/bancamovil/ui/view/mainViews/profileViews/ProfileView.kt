package com.horizon.bancamovil.ui.view.mainViews.profileViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.components.profileComponents.ComponentsProfile
import com.horizon.bancamovil.ui.components.profileComponents.ProfileStyle
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun ProfileViewRoute(
    navController: NavController,
    viewModel: BankingViewModel,
    authManager: AuthManager
) {

    val showSignOut = remember { mutableStateOf(false) }
    val state by viewModel.accountState.collectAsState()

    AlertSignOut(showSignOut.value, authManager, navController) {
        showSignOut.value = false
    }

    BasicOpeStyle(
        navController,
        viewModel,
        "Tu perfil",
        actions = {
            Icon(
                if (state.darkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                "change mode"
            )
            Spacer(Modifier.width(10.dp))
            Switch(
                state.darkMode,
                onCheckedChange = { viewModel.saveModeTheme(it) }
            )
        }
    ) {
        item {
            ProfileStyle(authManager) {
                ComponentsProfile(authManager, navController) {
                    showSignOut.value = true
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlertSignOut(
    signOut: Boolean,
    authManager: AuthManager,
    navController: NavController,
    onDismiss: () -> Unit
) {

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
                                    if (index == 0) onDismiss() else authManager.singOut(
                                        navController
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}