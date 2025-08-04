package com.horizon.bancamovil.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.BabyChangingStation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.BodySmall
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.theme.abelFont
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalancerTopAppBar(
    navController: NavController,
    title: String = "Estadísticas",
    color: Color = MaterialTheme.colorScheme.tertiary
) {
    TopAppBar(
        title = {
            HeadLineLarge(title)
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.Default.ArrowBackIosNew, "Back Navigation")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        )
    )
}

@Composable
fun MoneyAvailable() {
    BodyLarge("Disponible")
    Row {
        HeadLineLarge("$31,283,052")
        BodyMedium(
            text = ".74",
            fontWeight = FontWeight.W800,
            fontFamily = abelFont
        )
    }
    Box {
        Column {
            BodyMedium("Ganancias Generadas")
            Spacer(Modifier.height(5.dp))
            BodySmall("Obtuviste $ 303,425 en los últimos 12 meses")
        }
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .2f))
}

@Composable
fun HistoryMovements() {

    val listExampleCompany = listOf(
        "Google",
        "GMB",
        "Rendimiento Bursátil",
        "Google"
    )
    val listExampleTransaction = listOf(
        "Pago",
        "Transferencia",
        "Ganancias",
        "Pago"
    )
    val listExampleAmount = listOf(
        1000.00,
        500000.00,
        1000000.00,
        3000.00
    )
    val listExampleDate = listOf(
        "2 de Enero",
        "24 de Abril",
        "16 de Junio",
        "20 de Junio"
    )

    listExampleTransaction.take(3).forEachIndexed { index, s ->
        ContainerMovements(
            listExampleCompany[index],
            s,
            listExampleAmount[index],
            listExampleDate[index]
        )
    }
}

@Composable
fun ContainerMovements(
    company: String,
    transaction: String,
    amount: Double,
    dateTransaction: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(7.dp))
            TransactionContainer(company, transaction, modifier = Modifier.weight(2f))
            Spacer(Modifier.width(7.dp))
            TransactionAmount(amount, dateTransaction, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun TransactionContainer(
    company: String,
    transaction: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.onBackground.copy(
                            alpha = .2f
                        ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Rounded.BabyChangingStation,
                    "Company",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BodySmall(company)
                BodyMedium(transaction)
            }
        }
    }
}

@Composable
fun TransactionAmount(amount: Double, dateTransaction: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        BodyMedium("$ ${formatCurrency(amount)}")
        BodySmall(dateTransaction)
    }
}

fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = 0
    return formatter.format(amount)
}
