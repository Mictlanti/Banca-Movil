package com.horizon.bancamovil.ui.components.loginComponents

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.horizon.bancamovil.R
import com.horizon.bancamovil.ui.components.fontStyles.BodyLarge
import com.horizon.bancamovil.ui.components.fontStyles.BodyMedium
import com.horizon.bancamovil.ui.components.fontStyles.HeadLineLarge
import com.horizon.bancamovil.data.remote.firestore.AuthManager
import com.horizon.bancamovil.data.remote.firestore.AuthRes
import com.horizon.bancamovil.navigation.AppScreens
import com.horizon.bancamovil.ui.theme.poppins
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel
import kotlinx.coroutines.launch

@Composable
fun BackgroundSplash(painter: Int) {
    Image(
        painterResource(painter),
        null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .5f))
    )
}

@Composable
fun OneView(mainPhrase: String, secondaryPhrase: String) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(BottomAppBarDefaults.windowInsets)
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadLineLarge(
            mainPhrase,
            fontFamily = poppins,
            fontSize = 25.sp,
            letterSpacing = 4.sp,
            textAlign = TextAlign.Center
        )
        BodyLarge(secondaryPhrase)
    }
}

@Composable
fun SignInViewGoogle(navController: NavController, authManager: AuthManager, bankingViewModel: BankingViewModel) {

    val signInOption = listOf(
        "Registrate",
        "Inicia sesión"
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeadLineLarge(
            "Comienza tu libertad financiera",
            fontFamily = poppins,
            fontSize = 25.sp,
            letterSpacing = 4.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        BodyLarge("Inicia sesión:")
        Spacer(Modifier.height(10.dp))
        SignInWidth(signInOption, navController, authManager, bankingViewModel)
        Spacer(modifier = Modifier.weight(.2f))
    }
}

@Composable
fun NextOrBackScreen(pagerState: PagerState, modifier: Modifier = Modifier) {

    val nextPage = (pagerState.currentPage + 1).coerceIn(0, pagerState.pageCount - 1)
    val lastPage = (pagerState.currentPage - 1)
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedVisibility(pagerState.currentPage > 0) {
            ButtonNextOrReturn("Volver") {
                scope.launch {
                    pagerState.animateScrollToPage(
                        lastPage,
                        animationSpec = tween(
                            500
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(pagerState.currentPage < 3) {
            ButtonNextOrReturn("Siguiente") {
                scope.launch {
                    pagerState.animateScrollToPage(
                        nextPage,
                        animationSpec = tween(
                            500
                        )
                    )
                }
            }
        }
        AnimatedVisibility(pagerState.currentPage == 3) {
            ButtonNextOrReturn("Inicia sesión") {
                scope.launch {
                    pagerState.animateScrollToPage(
                        nextPage,
                        animationSpec = tween(
                            500
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CountingPages(pagerState: PagerState, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pagerState.pageCount) { int ->
            Box(
                modifier = Modifier
                    .background(
                        if (pagerState.currentPage == int) MaterialTheme.colorScheme.tertiary else Color.Gray.copy(
                            alpha = .8f
                        ),
                        shape = CircleShape
                    )
                    .animateContentSize()
                    .size(if (pagerState.currentPage == int) 15.dp else 8.dp)
            )
        }
    }
}

@Composable
fun ButtonNextOrReturn(text: String, onClick: () -> Unit) {

    ElevatedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        BodyMedium(
            text,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Composable
private fun SignInWidth(
    signInOption: List<String>,
    navController: NavController,
    authManager: AuthManager,
    bankingViewModel: BankingViewModel
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val googleSignInLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (val account =
                authManager.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))) {
                is AuthRes.Success -> {
                    val credential = GoogleAuthProvider.getCredential(account.data.idToken, null)
                    scope.launch {
                        val fireUser = authManager.signInWithGoogleCredential(credential)
                        if (fireUser != null) {
                            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                            navController.navigate(AppScreens.HomeView.route)
                        }
                    }
                }

                is AuthRes.Error -> {
                    Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        signInOption.forEachIndexed { index, s ->
            ButtonSing(
                modifier = Modifier.weight(1f),
                s,
                if (index == 0) Color.Transparent else MaterialTheme.colorScheme.tertiaryContainer,
                if(index == 0) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onTertiaryContainer,
                if(index == 1) R.drawable.icon_google else null
            ) {
                if(index == 0) {
                    navController.navigate(AppScreens.UnavailableView.route)
                } else {
                    bankingViewModel.initDataBase()
                    authManager.signInWithGoogle(googleSignInLauncher)
                }
            }
        }
    }
}

@Composable
private fun ButtonSing(
    modifier: Modifier,
    text: String,
    color: Color,
    colorText: Color,
    icon: Int? = null,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if(icon != null){
                Icon(
                    painter = painterResource(icon),
                    null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(Modifier.width(5.dp))
            }
            BodyMedium(text, color = colorText)
        }
    }
}