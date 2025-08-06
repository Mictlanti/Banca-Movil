package com.horizon.bancamovil

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.horizon.bancamovil.navigation.Navigation
import com.horizon.bancamovil.ui.theme.BancaMovilTheme
import com.horizon.bancamovil.ui.view.LoginViews.OnboardingViewRoute
import com.horizon.bancamovil.ui.viewmodel.BankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if(isGranted) {
                // Permiso concedido
            } else {
                //Permiso denegado
            }

            //Pedir permiso
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                ) {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }

        enableEdgeToEdge()
        setContent {

            val bankingViewModel : BankingViewModel = viewModel()
            val context = LocalContext.current

            BancaMovilTheme(bankingViewModel) {
                Navigation(bankingViewModel, context)
            }
        }
    }
}
