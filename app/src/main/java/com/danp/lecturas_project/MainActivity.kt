package com.danp.lecturas_project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.danp.lecturas_project.datastore.Preferencias
import com.danp.lecturas_project.navigation.Destinations.*
import com.danp.lecturas_project.navigation.NavigationHost
import com.danp.lecturas_project.presentation.components.BottomNavigationBar
import com.danp.lecturas_project.presentation.components.TopFBar
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val dataStore = Preferencias(context)
    val skipLogin = dataStore.getSkipSesion.collectAsState(initial = false).value
    val estadoSesion = dataStore.getEstadoSesion.collectAsState(initial = false).value
    val openLogin = remember { mutableStateOf(true) }
    if (openLogin.value){
        if (skipLogin || estadoSesion){
            openLogin.value = !openLogin.value
        }
        else {
            LoginScreen()
        }
    }
    if (!openLogin.value) {
        BottomNavigation(openLogin)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigation(openLogin: MutableState<Boolean>) {
    val navController= rememberNavController()
    val navigationItem = listOf(
        Home,
        Lecturas,
        Ranking,
        Perfil
    )

    Scaffold(
        topBar = { TopFBar(openLogin)},
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navigationItem)
        }
    ){
        NavigationHost(navController = navController)
    }
}