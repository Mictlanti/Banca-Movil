package com.horizon.bancamovil.components.HomeComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.rounded.Help
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.CellWifi
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.HMobiledata
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.filled.SettingsCell
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.BodySmall
import com.horizon.bancamovil.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.theme.abelFont
import com.horizon.bancamovil.ui.theme.robotoFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome() {
    TopAppBar(
        title = {
            Row(modifier = Modifier.clickable { }) {
                HeadLineLarge("Hola, Lolita", color = MaterialTheme.colorScheme.onTertiary)
                Icon(
                    Icons.AutoMirrored.Filled.ArrowRight,
                    "Go to Profile",
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        },
        actions = {
            Icon(
                Icons.Filled.Notifications,
                "Notifications",
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Spacer(Modifier.width(12.dp))
            Icon(
                Icons.AutoMirrored.Rounded.Help,
                "Help",
                tint = MaterialTheme.colorScheme.onTertiary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    )
}

@Composable
fun HomeBottomBar() {

    val bottomNavIcons = listOf(
        Icons.Default.Home,
        Icons.Default.LocalActivity,
        Icons.Default.QrCode,
        Icons.Default.Person,
        Icons.Default.Reorder
    )
    val bottomNavLabels = listOf(
        "Home",
        "Actividad",
        "",
        "Beneficios",
        "Más"
    )
    val windowInsets = WindowInsets.navigationBars

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            )
            .windowInsetsPadding(windowInsets),
//            .shadow(
//                elevation = 0.5.dp,
//                shape = MaterialTheme.shapes.small
//            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomNavIcons.forEachIndexed { index, vector ->
            BottomNavItem(vector, index, bottomNavLabels) {

            }
        }
    }
}

@Composable
private fun BottomNavItem(
    vector: ImageVector,
    index: Int,
    labels: List<String>,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = { onClick() },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = if (index == 2) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent
            ),
            modifier = Modifier
                .size(if (index == 2) 60.dp else 35.dp)
                .shadow(
                    elevation = if (index == 2) 5.dp else 0.dp,
                    shape = CircleShape
                )
        ) {
            Icon(
                vector,
                null,
                tint = if(index == 2) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(if (index == 2) 53.dp else 30.dp)
                    .padding(if (index == 2) 7.dp else 0.dp)
            )
        }
        BodySmall(labels[index], color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun SectionCard(aspectRatio: Float = 1f, content: @Composable() (ColumnScope.() -> Unit)) {
    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
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
fun AccountBalanceSection(onClick: () -> Unit) {

    var dismissMoney by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                BodyLarge("Disponible")
                Spacer(Modifier.width(10.dp))
                UrMoneyGrows()
            }
            BodyMedium(
                "Tu dinero está generando ganancias",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                HeadLineLarge("$" + if (dismissMoney) "31,283,052" else "***")
                BodyMedium(
                    text = if (dismissMoney) ".74" else "",
                    fontWeight = FontWeight.W800,
                    fontFamily = abelFont,
                    modifier = Modifier.align(Alignment.Top)
                )
                Spacer(Modifier.width(12.dp))
                IconButton(onClick = { dismissMoney = !dismissMoney }) {
                    if (dismissMoney) {
                        Icon(
                            Icons.Default.Visibility,
                            "Visibility",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    } else {
                        Icon(
                            Icons.Default.VisibilityOff,
                            "Visibility Off",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UrMoneyGrows() {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.Green.copy(alpha = .1f))
    ) {
        BodySmall(
            "Crece 1.3% anual",
            color = Color.Green,
            modifier = Modifier.padding(1.dp),
            fontSize = 11.sp
        )
    }
}

@Composable
fun BasicOperationBanking(navController: NavController) {

    val operation = listOf(
        "Ingresar",
        "Transferir",
        "Retirar",
        "Tu Clave"
    )
    val iconsOpe = listOf(
        Icons.Default.Analytics,
        Icons.Default.Face,
        Icons.Default.ArrowDownward,
        Icons.Default.HMobiledata
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        operation.forEachIndexed { index, s ->
            CardBasicOperationBanking(
                iconsOpe[index],
                s
            ) {
                when(index) {
                    0 -> navController.navigate(AppScreens.DepositView.route)
                    1 -> navController.navigate(AppScreens.TransferView.route)
                    2 -> navController.navigate(AppScreens.WithdrawView.route)
                    3 -> navController.navigate(AppScreens.YourKeyView.route)
                }
            }
        }
    }

}

@Composable
private fun CardBasicOperationBanking(
    imageVector: ImageVector,
    value: String,
    colorCard: Color = MaterialTheme.colorScheme.secondary.copy(alpha = .2f),
    colorText: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            onClick = { onClick() },
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = colorCard
            )
        ) {
            Icon(
                imageVector,
                value,
                modifier = Modifier
                    .size(62.dp)
                    .padding(10.dp)
            )
        }
        BodySmall(value, color = colorText)
    }
}

@Composable
fun CreditsStyle(highlightText: String) {
    Text(
        text = buildAnnotatedString {
            append("Hasta ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            ) {
                append(highlightText)
            }
            append(" disponibles para comprar a meses sin intereses.")
        },
        fontFamily = robotoFont,
        fontSize = 13.sp,
        fontWeight = FontWeight.W300,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun PayServicesMenu() {

    val services = listOf(
        "Recarga\nCelular",
        "Servicios",
        "Recargar TAG",
        "Dinero del \nExtrangero",
        "Recargar \ntransporte",
        "Pagos de\nGobierno",
        "Ver más"
    )
    val iconServices = listOf(
        Icons.Default.CellWifi,
        Icons.Default.CellTower,
        Icons.Default.SettingsCell,
        Icons.Default.Money,
        Icons.Default.Train,
        Icons.Default.FoodBank,
        Icons.Default.Add
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            services.take(4).forEachIndexed { index, s ->
                CardBasicOperationBanking(iconServices[index], s) { }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            services.takeLast(2).forEachIndexed { index, s ->
                CardBasicOperationBanking(iconServices[index + 4], s) { }
            }
        }
    }

}

@Composable
fun FavoriteContectList() {
    val listPersons = listOf(
        "Add",
        "Grabiela Mátinez Becerril",
        "Claudia Allende Rizzi"
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(listPersons) { index, item ->
            CardBasicOperationBanking(
                if (index == 0) Icons.Default.Add else Icons.Default.Person,
                formatName(item),
                MaterialTheme.colorScheme.background.copy(alpha = .5f),
                MaterialTheme.colorScheme.background
            ) { }
        }
    }

}

private fun formatName(name: String): String {
    val parts = name.split(" ")
    return if (parts.size >= 2) {
        val namePart = parts[0]
        val firstName = parts[1].firstOrNull()?.uppercase() ?: ""
        "$namePart $firstName."
    } else {
        name
    }
}