package com.horizon.bancamovil.ui.view.othersView

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun ContactMeRoute(navController: NavController, viewModel: BankingViewModel) {

    val ctx = LocalContext.current
    val linkedInUrk = "https://www.linkedin.com/in/mihmatikapp/"
    val githubUrl = "https://github.com/Mictlanti"

    BasicOpeStyle(navController, viewModel, "Contacto") {
        item { Spacer(Modifier.height(50.dp)) }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HeadLineLarge("LinkedIn: ")
                Spacer(Modifier.width(20.dp))
                BodyMedium("Ir al link",
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrk))
                            ctx.startActivity(intent)
                        }
                )
            }
        }
        item { Spacer(Modifier.height(20.dp)) }
        item {

            Row(verticalAlignment = Alignment.CenterVertically) {
                HeadLineLarge("GitHub: ")
                Spacer(Modifier.width(20.dp))
                BodyMedium(
                    "Ir al link",
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                            ctx.startActivity(intent)
                        }
                )
            }
        }
    }
}