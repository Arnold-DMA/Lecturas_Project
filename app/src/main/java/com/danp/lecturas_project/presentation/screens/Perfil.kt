package com.danp.lecturas_project.presentation.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button


import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController

@Composable
fun Perfil(navController: NavHostController) {
    var imagenBitmap by rememberSaveable{ mutableStateOf<Bitmap?>(null)}
    var imagenUri by rememberSaveable{ mutableStateOf<Uri?>(null)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TomarImagen(onImageCapture = {imagenBitmap = it}, onUriCapture = {imagenUri = it})
    }
}

@Composable
fun TomarImagen(onImageCapture: (Bitmap?) -> Unit, onUriCapture: (Uri?) -> Unit) {

    val context = LocalContext.current
    var bitmapDos by  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val camaraOn = remember{ mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { btm: Bitmap? ->
        bitmapDos = btm
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            if (camaraOn.value){
                cameraLauncher.launch()
            }
            Toast.makeText(context, "Permiso concedido!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permiso denegado!", Toast.LENGTH_SHORT).show()
        }
    }
    Row() {
        Button(onClick = {
            camaraOn.value = true
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context, Manifest.permission.CAMERA
                ) -> {
                    cameraLauncher.launch()
                }
                else -> {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }) {
            Text(text = "Camara")
        }
    }

    if (camaraOn.value) {
        bitmapDos?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
            onImageCapture(btm)
            onUriCapture(null)
        }
    }

}