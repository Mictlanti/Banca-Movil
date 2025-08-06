package com.horizon.bancamovil.ui.components.BasicOpeComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium

@Composable
fun SectionDataUser(mexicoSelected: Boolean, nextPage : () -> Unit, lastPage: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { nextPage() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BodyLarge(
                "Desde México",
                color = if (!mexicoSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary.copy(alpha = .5f)
            )
            HorizontalDivider(color = if (!mexicoSelected) MaterialTheme.colorScheme.secondary else Color.Transparent)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { lastPage() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BodyLarge(
                "Desde el extranjero",
                color = if (mexicoSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary.copy(alpha = .5f)
            )
            HorizontalDivider(color = if (mexicoSelected) MaterialTheme.colorScheme.secondary else Color.Transparent)
        }
    }
}

@Composable
fun SharedDataUserKey(listDataNational: List<Pair<String, String>>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        listDataNational.forEachIndexed { index, (type, data) ->
            DataUserNational(type, data)
            if (listDataNational.size > index + 1)
                HorizontalDivider(color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .1f))
        }
    }
}

@Composable
fun DataUserNational(type: String, dataUser: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp)
    ) {
        BodyMedium(
            type,
            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .5f),
            modifier = Modifier.padding(start = 10.dp)
        )
        BodyMedium(
            dataUser,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun SharedDataUserForeign(listDataForeign: List<Pair<String, String>>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        listDataForeign.forEachIndexed { index, (type, data) ->
            DataUserNational(type, data)
            if (listDataForeign.size > index + 1)
                HorizontalDivider(color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = .1f))
        }
    }
}