package com.example.dummyjsonapp

import Login_Screen
import com.example.dummyjsonapp.Screens.Register_Screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dummyjsonapp.Screens.Page_Api

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = routrs.LOGIN) {
                composable(routrs.LOGIN) {
                    Login_Screen(navController = navController)
                }

                composable(routrs.REGISTER) {
                    Register_Screen(navController = navController)
                }
                composable(routrs.HOMEPAGE) {
                    Page_Api(
                        navController = navController,
                    )
                }

            }
        }
    }
}