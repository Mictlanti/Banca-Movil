package com.horizon.bancamovil

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.horizon.bancamovil.navigation.Navigation
import com.horizon.bancamovil.ui.theme.BancaMovilTheme

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
            BancaMovilTheme {
                Navigation()
            }
        }
    }
}
