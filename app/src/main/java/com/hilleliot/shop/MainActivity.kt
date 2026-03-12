package com.hilleliot.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hilleliot.shop.ui.composable.approot.AppRoot
import com.hilleliot.shop.ui.theme.HLELTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HLELTTheme {
                AppRoot()
            }
        }
    }
}
