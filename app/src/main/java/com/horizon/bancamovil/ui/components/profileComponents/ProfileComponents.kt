package com.horizon.bancamovil.ui.components.profileComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.horizon.bancamovil.R
import com.horizon.bancamovil.ui.components.BasicOpeComponents.DepositWithCard
import com.horizon.bancamovil.ui.components.HomeComponents.SectionCard
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.navigation.AppScreens

@Composable
fun ProfileStyle(authManager: AuthManager, components: @Composable () -> Unit) {

    //val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SectionCard(
            .6f,
            MaterialTheme.colorScheme.tertiary,
            Modifier
                .padding(top = 65.dp),
            padding = 0.dp
        ) {
            components()
        }
        ImageProfile(
            authManager,
            Modifier
                .clip(CircleShape)
                .size(130.dp)
                .align(Alignment.TopCenter)
                .zIndex(1f)
        )
    }
}

@Composable
fun ImageProfile(authManager: AuthManager, modifier: Modifier = Modifier) {

    val user = authManager.getCurrentUser()

    if (user?.photoUrl != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.photoUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Image",
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image",
            modifier = modifier
        )
    }
}

@Composable
fun ComponentsProfile(authManager: AuthManager, navController: NavController, signOutSession: () -> Unit) {

    val user = authManager.getCurrentUser()?.displayName ?: ""
    val email = authManager.getCurrentUser()?.email ?: ""
    val optionsProfile = listOf(
        "Datos de la cuenta",
        "Tarjetas enlazadas",
        "Acerca de",
        "Contacto"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        HeadLineLarge(
            user,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        BodyLarge(
            email,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.onTertiary.copy(alpha = .2f))
        OptionsProfile(optionsProfile, navController)
        Spacer(modifier = Modifier.height(20.dp))
        BodyMedium(
            "Cerrar sesión",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { signOutSession() }
        )
    }
}

@Composable
fun OptionsProfile(optionsProfile: List<String>, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        optionsProfile.forEachIndexed { index, s ->
            DepositWithCard(
                s,
                "",
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.onTertiary
            ) {
                when(index) {
                    0 -> navController.navigate(AppScreens.DetailsView.route)
                    2 -> navController.navigate(AppScreens.AboutView.route)
                    3 -> navController.navigate(AppScreens.ContactMeView.route)
                }
            }
        }
    }
}