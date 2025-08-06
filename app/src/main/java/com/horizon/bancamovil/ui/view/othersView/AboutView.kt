package com.horizon.bancamovil.ui.view.othersView

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.horizon.bancamovil.ui.components.BasicOpeComponents.BasicOpeStyle
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.BodySmall
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel

@Composable
fun AboutViewRoute(navController: NavController, viewModel: BankingViewModel) {

    val ctx = LocalContext.current
    val githubUrl = "https://github.com/Mictlanti"

    BasicOpeStyle(navController, viewModel, "Banca Molvil App") {
        item {
            BodyMedium(
                "Esta aplicación de Banca Móvil ha sido desarrollada con fines exclusivamente educativos y de práctica en programación. " +
                        "\nNo está vinculada a ninguna institución bancaria real, y no debe ser utilizada para realizar " +
                        "transacciones financieras reales." +
                        "\nSu propósito es servir como ejemplo de desarrollo de aplicaciones móviles seguras y funcionales, " +
                        "aplicando buenas prácticas en Kotlin y Jetpack Compose.\n"
            )
        }
        item {
            BodyLarge("Características:")
        }
        item {
            BodySmall(
                "Inicio de sesión con autenticación segura (Firebase Auth).\n" +
                        "\n" +
                        "Simulación de transferencias y movimientos bancarios.\n" +
                        "\n" +
                        "Historial de transacciones.\n" +
                        "\n" +
                        "Sección de perfil personalizable.\n" +
                        "\n" +
                        "Diseño moderno con Jetpack Compose.\n" +
                        "\n" +
                        "Implementación de buenas prácticas en arquitectura MVVM."
            )
        }
        item {
            BodyMedium("Desarrollada por: Daniel Rosas")
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BodyMedium(
                    "GitHub: GitHub:",
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                            ctx.startActivity(intent)
                        }
                )
                Spacer(Modifier.width(20.dp))
                BodyMedium(
                    "Ir al link",
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        item {
            BodyMedium("Versión 1.0.0 (Build de aprendizaje)")
        }
    }
}