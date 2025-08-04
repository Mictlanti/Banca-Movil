package com.horizon.bancamovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.horizon.bancamovil.navigation.Navigation
import com.horizon.bancamovil.ui.theme.BancaMovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BancaMovilTheme {
                Navigation()
            }
        }
    }
}
