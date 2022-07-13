package com.danp.lecturas_project.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.danp.lecturas_project.ui.theme.Purple500
import com.danp.lecturas_project.ui.theme.Purple700

@Composable
fun Lecturas(navController: NavHostController) {
    val eleccion = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (eleccion.value) {
            botones(eleccion, navController)
        }
        else {
            OpenLectura(eleccion = eleccion, navController = navController)
        }
    }
}

@Composable
fun botones(eleccion: MutableState<Boolean>, navController: NavHostController) {
    Text(
        text = "Tu última lectura.",
        style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
    )
    Button(onClick = {
        eleccion.value = !eleccion.value
    },
        elevation =  ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(4.dp, Purple700),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black, backgroundColor = Purple500)
    ) {
        Text(text = "Iniciar una nueva lectura al azar.", Modifier.padding(start = 10.dp))
    }
}

@Composable
fun OpenLectura(eleccion: MutableState<Boolean>, navController: NavHostController) {
    Text(
        text = "Lectura al azar",
        style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
    )
}
