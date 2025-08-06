package com.horizon.bancamovil.ui.components.BasicOpeComponents

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.domain.model.DepositSite
import com.horizon.bancamovil.domain.state.DataUser
import com.horizon.bancamovil.domain.state.DepositState
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel
import kotlinx.coroutines.launch

@Composable
fun HiddenNumberInput(
    state: DepositState,
    viewModel: BankingViewModel,
    accountState: DataUser,
    color: Color,
    showMoneyAvailable: Boolean = true,
    message: String? = null,
    maxDeposit: Boolean = true
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        viewModel.reformatValues()
    }


    Column(modifier = Modifier.padding(16.dp)) {

        BasicTextField(
            value = state.rawText,
            onValueChange = {
                viewModel.events(BankingEvents.OnValueChange(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .size(1.dp)
                .focusRequester(focusRequester)
        )

        ElevatedCard(
            onClick = {
                focusRequester.requestFocus()
                keyboardController?.show()
            },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.aspectRatio(2f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BodyLarge(
                    "$ ${viewModel.formatText().ifEmpty { "0" }}",
                    color = color,
                    fontSize = 40.sp
                )
                if (showMoneyAvailable) BodySmall(
                    message ?: "$ ${viewModel.formatCurrency(accountState.valueAccount)}",
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        if (maxDeposit) BodySmall("*Toma en cuenta el monto máximo del sitio")
    }
}

@Composable
fun CardSkeleton(
    site: String,
    amount: String,
    imageVector: Int,
    cost: String = "Gratis",
    free: Boolean = true,
    index: Int,
    state: DepositState,
    viewModel: BankingViewModel,
    onClick: () -> Unit
) {

    val counting = remember { mutableStateOf(0) }

    LaunchedEffect(counting.value) {
        if (counting.value == 1) viewModel.events(BankingEvents.AvailableSitesDeposit(null))
    }

    DepositInCard(
        index = index,
        state = state,
        counting = counting.value,
        onClick = {
            if (state.depositInSites == index) {
                if (counting.value == 1) {
                    counting.value = 0
                } else {
                    counting.value++
                }
            } else {
                counting.value = 0
            }
            onClick()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(imageVector),
                "Image",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(80.dp)
            )
            Spacer(Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
            ) {
                BodyLarge(site, color = MaterialTheme.colorScheme.onTertiaryContainer)
                BodyMedium(
                    cost,
                    color = if (free) Color.Green else MaterialTheme.colorScheme.onTertiaryContainer
                )
                BodyMedium(amount, color = MaterialTheme.colorScheme.onTertiaryContainer)
            }
        }
    }
}

@Composable
private fun DepositInCard(
    aspectRatio: Float = 3f,
    index: Int,
    state: DepositState,
    counting: Int,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        onClick = { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (index == state.depositInSites && counting != 1) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .aspectRatio(aspectRatio)
    ) {
        content()
    }
}

@Composable
fun WithDrawBottomAppBar(
    state: DepositState,
    selectedSize: DepositSite?,
    enabled: Boolean,
    onClickContinue: () -> Unit
) {

//    val nullables = if(state.rawText.isEmpty()) 0.0 else state.rawText.toDouble()
//    val condition = nullables !in 0.0..selectedSize?.maxAmount!!
    val amount = state.rawText.toDoubleOrNull() ?: 0.0
    val condition = selectedSize?.maxAmount?.let { amount in 0.0..it } ?: false
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                SwitchCard(state.rawText.isNotBlank())
                Spacer(Modifier.width(10.dp))
                SwitchCard(state.depositInSites != null)
            }
            Button(
                onClick = {
                    if(!condition) {
                        scope.launch {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        onClickContinue()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (
                        condition && state.depositInSites != null
                        ) {
                        MaterialTheme.colorScheme.secondaryContainer
                    }else {
                        Color.Gray.copy(
                            alpha = .5f
                        )
                    }
                ),
                enabled = enabled,
                modifier = Modifier.weight(4f)
            ) {
                BodyMedium(
                    "Continuar",
                    color = if (condition && state.depositInSites != null) MaterialTheme.colorScheme.onSecondaryContainer else Color.Gray.copy(
                        alpha = .5f
                    )
                )
                Log.d("Withdraw", "value: ${state.depositInSites}")
            }
        }
    }
}

@Composable
private fun SwitchCard(available: Boolean) {
    ElevatedCard(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = if (available) Color.Green else Color.Gray.copy(alpha = .5f)
        ),
        modifier = Modifier
            .size(18.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.tertiary.copy(alpha = if (available) 1f else 0f),
                shape = CircleShape
            )
    ) { }
}