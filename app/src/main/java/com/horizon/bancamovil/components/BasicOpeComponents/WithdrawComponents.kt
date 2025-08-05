package com.horizon.bancamovil.components.BasicOpeComponents

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
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.bancamovil.components.HomeComponents.SectionCard
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.BodySmall
import com.horizon.bancamovil.components.formatCurrency

@Composable
fun HiddenNumberInput() {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    var rawText by remember { mutableStateOf(TextFieldValue("")) }
    var formattedText by remember { mutableStateOf("") }
    val numericValue = rawText.text.toDoubleOrNull()
    val color = if (numericValue != null && numericValue <= 2000.0 || rawText.text.isBlank()) {
        MaterialTheme.colorScheme.onTertiary
    } else {
        MaterialTheme.colorScheme.errorContainer
    }


    Column(modifier = Modifier.padding(16.dp)) {

        BasicTextField(
            value = rawText,
            onValueChange = {
                rawText = it
                val numeric = it.text.toDoubleOrNull()
                formattedText = if (numeric != null) {
                    formatCurrency(numeric)
                } else {
                    ""
                }
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
                    "$ ${formattedText.ifEmpty { "0" }}",
                    color = color,
                    fontSize = 40.sp
                )
                BodySmall(
                    "$ 31,283,052.75 disponibles",
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        BodySmall("*Toma en cuenta el monto máximo del sitio")
    }
}

@Composable
fun CardSkeleton(
    site: String,
    amount: String,
    imageVector: Int,
    cost: String = "Gratis",
    free: Boolean = true
) {
    SectionCard(3f) {
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
fun WithDrawBottomAppBar(switchSt: Boolean, switchNd: Boolean) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                SwitchCard(switchSt)
                Spacer(Modifier.width(10.dp))
                SwitchCard(switchNd)
            }
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (switchSt && switchNd) MaterialTheme.colorScheme.secondaryContainer else Color.Gray.copy(
                        alpha = .5f
                    )
                ),
                modifier = Modifier.weight(3f)
            ) {
                BodyMedium(
                    "Continuar",
                    color = if (switchSt && switchNd) MaterialTheme.colorScheme.onSecondaryContainer else Color.Gray.copy(
                        alpha = .5f
                    )
                )
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
            .size(30.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.tertiary.copy(alpha = if (available) 1f else 0f),
                shape = CircleShape
            )
    ) { }
}