package com.horizon.bancamovil.ui.view.mainViews.profileViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.BasicOpeComponents.TextFieldStyle
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun AccountDetailsRoute(navController: NavController, viewModel: BankingViewModel) {

    val accountState by viewModel.accountState.collectAsState()
    val listDetails = listOf(
        "Nombre y apellidos" to "${ accountState.name ?: "- -" } ${ accountState.lastName ?: "- -" }",
        "Número de seguro social" to (accountState.nss ?: "- -").toString(),
        //Other Card
        "Domicilio particular" to "${accountState.district ?: "- -"} / ${accountState.neighborhood ?: "- -"} / ${accountState.postCode ?: "- -"}"
    )
    val modifierName = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf(false) }
    val detailOne = remember { mutableStateOf("") }
    val detailTwo = remember { mutableStateOf("") }
    val detailThree = remember { mutableStateOf("") }

    ModifierDetailsAccount(
        modifierName.value,
        selectedOption.value,
        detailOne,
        detailTwo,
        detailThree,
        dismiss = {
            modifierName.value = false
            selectedOption.value = false
        }
    ) {
        if(!selectedOption.value) {
            viewModel.events(
                BankingEvents.SelectedBeneficiary(
                    name = detailOne.value,
                    lastName = detailTwo.value,
                    nss = detailThree.value.toInt()
                )
            )
            detailOne.value = ""
            detailTwo.value = ""
            detailThree.value = ""
        } else {
            viewModel.events(
                BankingEvents.SelectedAddress(
                    district = detailOne.value,
                    neighborhood = detailTwo.value,
                    postCode = detailThree.value.toInt()
                )
            )
            detailOne.value = ""
            detailTwo.value = ""
            detailThree.value = ""
        }
    }

    BasicOpeStyle(navController, viewModel, "Datos de la cuenta") {
        item {
            SectionCard(1.4f) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listDetails.take(2).forEachIndexed { index, (details, values) ->
                        BodyLarge(details, color = MaterialTheme.colorScheme.onTertiaryContainer)
                        BodyMedium(values, color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .5f))
                        Spacer(modifier = Modifier.height(10.dp))
                        if(index == 1) BodyLarge(
                            "Modificar",
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clickable {
                                modifierName.value = true
                                selectedOption.value = false
                            }
                        )
                    }
                }
            }
        }
        item { Spacer(Modifier.height(40.dp)) }
        item {
            SectionCard(2.1f) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                ) {
                    listDetails.takeLast(1).forEach { (details, values) ->
                        BodyLarge(details, color = MaterialTheme.colorScheme.onTertiaryContainer)
                        BodyMedium(
                            values,
                            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .5f)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        BodyLarge(
                            "Modificar",
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clickable {
                                modifierName.value = true
                                selectedOption.value = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModifierDetailsAccount(
    isSelected: Boolean,
    optionSelected: Boolean,
    detailOne: MutableState<String>,
    detailTwo: MutableState<String>,
    detailThree: MutableState<String>,
    dismiss: () -> Unit,
    saveDetails: () -> Unit
) {


    val options = listOf(
        "Volver", "Aplicar"
    )

    if(isSelected) {
        BasicAlertDialog(
            dismiss,
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
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                BodyLarge("Completa la siguiente información")
                TextFieldStyle(
                    value = detailOne.value,
                    onValueChange = { detailOne.value = it },
                    label = if(!optionSelected) "Nombres" else "Distrito / Ciudad",
                    vector = null
                )
                TextFieldStyle(
                    value = detailTwo.value,
                    onValueChange = { detailTwo.value = it },
                    label = if(!optionSelected) "Apellidos" else "Colonia",
                    vector = null
                )
                TextFieldStyle(
                    value = detailThree.value,
                    onValueChange = { detailThree.value = it },
                    label = if(!optionSelected) "Número de seguro social" else "Código postal",
                    vector = null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    options.forEachIndexed { index, s ->
                        BodyMedium(
                            s,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clickable {
                                if(index == 0) {
                                    dismiss()
                                } else {
                                    if(detailOne.value.isNotBlank() && detailTwo.value.isNotBlank() && detailThree.value.isNotBlank()) {
                                        saveDetails()
                                        dismiss()
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }
}