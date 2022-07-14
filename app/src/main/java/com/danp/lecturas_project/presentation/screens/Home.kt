package com.danp.lecturas_project.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.danp.lecturas_project.R
private val morado = Color(0x800080)
@Composable
fun Home(navController: NavHostController) {
    Column( modifier = Modifier
        .padding(12.dp)
        .fillMaxSize()) {
        Text(
            text = "Bienvenid@ !!!",

            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black , fontFamily = FontFamily.Serif)
        )
        Row(modifier = Modifier
            .padding(10.dp)) {
            Text(
                text = "Sabias que..",

                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Black , fontFamily = FontFamily.Monospace)
            )
            Image(painter = painterResource(id = R.drawable.pregunta), contentDescription = "pregunta", modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .height(150.dp))
        }

        Text(
            text = "”Los niveles de aprendizaje nunca han sido muy altos en Perú en comparación con otros países de la región, pero hay un decrecimiento importante respecto a cómo estábamos hace dos años”",

            style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black , fontFamily = FontFamily.Serif, textAlign = TextAlign.Center)
        )
        Image(painter = painterResource(id = R.drawable.bajoporcentaje), contentDescription = "pregunta", modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .height(250.dp))
        Text(
            text = "Por ello te presentamos esta aplicacion que te ayudara a mejorar tu niveles de lectura y concetracion.",

            style = TextStyle(color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Black , fontFamily = FontFamily.Serif, textAlign = TextAlign.Center)
        )


    }



}
