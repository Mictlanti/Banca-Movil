package com.horizon.bancamovil.ui.view.BasicOpeBankingView.extraTranfer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.TextFieldStyle
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun ToCelViewOne(navController: NavController, viewModel: BankingViewModel) {

    val contacts = remember { mutableStateOf("") }

    BasicOpeStyle(navController, viewModel) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HeadLineLarge("Ingresa un número de celular, e-mail o nombre del contacto")
                TextFieldStyle(
                    value = contacts.value,
                    onValueChange = { contacts.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = ""
                )
            }
        }
        item {
            BodyMedium("Tus contactos")
        }
    }

}