package com.horizon.bancamovil.ui.components.BasicOpeComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.CommentBank
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.domain.state.DepositState
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel
import kotlinx.coroutines.launch

@Composable
fun TransferOptionsCard(vector: ImageVector, value: String, comment: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    vector,
                    value,
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.size(40.dp)
                )
                Column {
                    BodyLarge(value, color = MaterialTheme.colorScheme.onTertiary)
                    BodyMedium(comment, color = MaterialTheme.colorScheme.onTertiary)
                }
            }
            Icon(
                Icons.AutoMirrored.Filled.ArrowRight,
                null,
                tint = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}

@Composable
fun SearchAndFavoritesSection(state: DepositState) {

    val listContacts = listOf(
        "Joan Sebastian" to "Banamex",
        "Scarlett Johanson" to "BBVA"
    )

    SectionCard(.7f) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            item {
                TopBarSearchAndFavorite()
            }
            item {
                Spacer(Modifier.height(12.dp))
            }
            itemsIndexed(state.transferHistory) { index, state ->
                Contacts(state.titular, state.institutionBank)
            }
        }
    }
}

@Composable
private fun TopBarSearchAndFavorite() {

    val value = remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextFieldStyle(
            value.value,
            onValueChange = { value.value = it },
            modifier = Modifier.weight(3f)
        )
        IconButton(
            onClick = { },
            modifier = Modifier.weight(1f)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                Icon(
                    Icons.Default.Favorite,
                    "Favorites",
                    tint = Color.Red
                )
                BodySmall("Favoritos")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldStyle(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Buscar frecuentes",
    vector: ImageVector? = Icons.Default.Search,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        textStyle = textStyle,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.onTertiary,
            focusedTextColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
            focusedLabelColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
            unfocusedLabelColor = MaterialTheme.colorScheme.onTertiary,
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f),
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiary
        ),
        placeholder = placeholder,
        leadingIcon = { if (vector != null) Icon(vector, "Search") },
        keyboardOptions = keyboardOptions,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
    )
}

@Composable
private fun Contacts(nameDestination: String, banking: String) {

    val parts = nameDestination.trim().split("\\s+".toRegex())
    val initials = parts.take(2)
        .map { it.first().uppercaseChar() }
        .joinToString("")


    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                            CircleShape
                        )
                ) {
                    BodyLarge(
                        initials,
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Column {
                    BodyMedium(nameDestination)
                    BodySmall(banking)
                }
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Favorite, "Add Favorite")
            }
        }
    }
}

@Composable
fun HorizontalDetailsAccount(
    pagerState: PagerState,
    keyTarget: MutableState<String>,
    titularTarget: MutableState<String>,
    institutionBank: MutableState<String>,
    viewModel: BankingViewModel,
    enabled: Boolean,
    onClickFinish: () -> Unit
) {

    val nextPage = (pagerState.currentPage + 1).coerceIn(0, pagerState.pageCount - 1)
    val lastPage = pagerState.currentPage - 1

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconNextOrBack(
                Icons.AutoMirrored.Filled.ArrowBack,
                pagerState,
                lastPage,
                pagerState.currentPage > 0
            )
            IconNextOrBack(
                Icons.AutoMirrored.Filled.ArrowForward,
                pagerState,
                nextPage,
                (keyTarget.value.length == 16 || keyTarget.value.length == 18) && viewModel.operationsAvailable()
            )
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {

                0 -> {
                    PageStyle(
                        "Número de tarjeta o CLABE",
                        "16 dígitos para tarjeta o 18 para el uso de CLABE",
                        keyTarget.value,
                        onValueChange = {
                            if(it.length <= 18) keyTarget.value = it
                        },
                        null,
                        "",
                        KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyTarget.value.length
                    )
                }

                1 -> {
                    PageStyle(
                        "Nombre del titular",
                        "",
                        titularTarget.value,
                        onValueChange = { titularTarget.value = it },
                        Icons.Default.PermIdentity,
                        "Titular",
                        KeyboardOptions(keyboardType = KeyboardType.Text),
                        fontSize = 18.sp
                    )
                }

                2 -> {
                    PageStyle(
                        "Institución bancaria",
                        "",
                        institutionBank.value,
                        onValueChange = { institutionBank.value = it },
                        Icons.Default.CommentBank,
                        "Institución",
                        KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                }

                3 -> FinishPage(onClickFinish, enabled)

            }
        }
    }
}

@Composable
private fun PageStyle(
    title: String,
    instruction: String,
    value: String,
    onValueChange: (String) -> Unit,
    vector: ImageVector?,
    label: String,
    keyboardOptions: KeyboardOptions,
    keyLength: Int? = 0,
    fontSize: TextUnit = 25.sp
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadLineLarge(title)
        Spacer(Modifier.height(40.dp))
        BodySmall(instruction)
        TextFieldStyle(
            value = value,
            onValueChange = { onValueChange(it) },
            label = label,
            vector = vector,
            keyboardOptions = keyboardOptions,
            textStyle = TextStyle(fontSize = fontSize),
            modifier = Modifier.fillMaxWidth()
        )
        if (keyLength != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BodySmall("$keyLength / 18")
            }
        }
    }
}

@Composable
fun TransferCard(
    pagerState: PagerState,
    state: DepositState,
    viewModel: BankingViewModel,
    titularCard: String,
    keyTarget: String,
    institution: String,
    color: Color,
    onClickEnabled: Boolean = true
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.padding(16.dp)) {

        if (pagerState.currentPage >= 2) {
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
        }

        ElevatedCard(
            onClick = {
                if(onClickEnabled){
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.aspectRatio(1.7f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                if (pagerState.currentPage >= 2) {
                    BodyLarge(
                        "$ ${viewModel.formatText().ifEmpty { "0" }}",
                        color = color,
                        fontSize = 40.sp
                    )
                }
                BodyMedium(
                    "Titular: $titularCard",
                    color = color,
                    modifier = Modifier.align(Alignment.Start)
                )
                BodyMedium(
                    "Clave: $keyTarget",
                    color = color,
                    modifier = Modifier.align(Alignment.Start)
                )
                BodyMedium(
                    "Institución: $institution",
                    color = color,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@Composable
private fun IconNextOrBack(
    vector: ImageVector,
    pagerState: PagerState,
    nextOrLastPage: Int,
    enabled: Boolean
) {

    val scope = rememberCoroutineScope()

    IconButton(
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(nextOrLastPage)
            }
        },
        enabled = enabled
    ) {
        Icon(
            vector,
            null,
            tint = if(enabled) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .5f)
        )
    }
}

@Composable
private fun FinishPage(onClickFinish: () -> Unit, enabled: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BodyMedium("Corrobora la información antes de continuar")
        ElevatedButton(
            onClick = {
                onClickFinish()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            BodyLarge("Continuar", color = MaterialTheme.colorScheme.secondaryContainer)
        }
    }
}