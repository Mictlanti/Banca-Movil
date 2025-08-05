package com.horizon.bancamovil.components.BasicOpeComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.components.BalancerTopAppBar
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.BodySmall

@Composable
fun BasicOpeStyle(
    navController: NavController,
    title: String = "",
    bottomBar : @Composable () -> Unit = {} ,
    content: LazyListScope.() -> Unit
) {
    Scaffold(
        topBar = {
            BalancerTopAppBar(
                navController,
                title = title,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        },
        bottomBar = {
            bottomBar()
        },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
           content()
        }
    }
}

@Composable
fun DataAccount() {
    ElevatedCard(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BodyLarge("Clave", color = MaterialTheme.colorScheme.onTertiary)
            BodySmall("11 5461 1548 5555 0000", color = MaterialTheme.colorScheme.onTertiary)
            HorizontalDivider(color = MaterialTheme.colorScheme.onTertiary.copy(alpha = .2f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Share,
                        "Share",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                    BodyMedium(
                        "Compatir datos",
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun DepositWithCard(label: String, comments: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = { onClick() },
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                BodyMedium(label, color = MaterialTheme.colorScheme.onTertiaryContainer)
                Spacer(Modifier.height(2.dp))
                if(comments.isNotBlank()) BodySmall(comments, color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .3f))
            }
            Icon(
                Icons.AutoMirrored.Filled.ArrowRight,
                label,
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}