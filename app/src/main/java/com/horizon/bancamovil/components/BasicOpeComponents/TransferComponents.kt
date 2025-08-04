package com.horizon.bancamovil.components.BasicOpeComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.horizon.bancamovil.components.HomeComponents.SectionCard
import com.horizon.bancamovil.components.fontStyles.BodyLarge
import com.horizon.bancamovil.components.fontStyles.BodyMedium
import com.horizon.bancamovil.components.fontStyles.BodySmall

@Composable
fun TransferOptionsCard(vector: ImageVector, value: String, comment: String) {
    ElevatedCard(
        onClick = {  },
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
fun SearchAndFavoritesSection() {

    val listContacts = listOf(
        "Joan Sebastian" to "Banamex",
        "Scarlett Johanson" to "BBVA"
    )

    SectionCard(.7f) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TopBarSearchAndFavorite()
            Spacer(Modifier.height(12.dp))
            listContacts.forEach { (d, bank) ->
                Contacts(d, bank)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarSearchAndFavorite() {

    val value = remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value.value,
            onValueChange = { value.value = it},
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                cursorColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                focusedLabelColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = .6f),
                unfocusedLabelColor = MaterialTheme.colorScheme.onTertiary,
                focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                focusedLeadingIconColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = .6f),
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiary
            ),
            leadingIcon = { Icon(Icons.Default.Search, "Search") },
            label = { Text(text = "Buscar frecuentes") },
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier.weight(2f)
        )
        IconButton(
            onClick = {  },
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

@Composable
private fun Contacts(nameDestination: String, banking: String) {
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
                        "JS",
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Column {
                    BodyMedium(nameDestination)
                    BodySmall(banking)
                }
            }
            IconButton(onClick = {  }) {
                Icon(Icons.Default.Favorite, "Add Favorite")
            }
        }
    }
}