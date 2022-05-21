package com.example.registro

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registro.ui.theme.RegistroTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var  settingsManager: SettingsManager

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //recuperando info
        //v
        var receptorT = false
        setContent {
            //var valT=valueColor()
            val darkMode = remember {
                mutableStateOf(false)
            }
            var textPA by remember {
                mutableStateOf("")
            }
            var textEP by remember {
                mutableStateOf("")
            }
            var textCA by remember {
                mutableStateOf("")
            }
            var textS by remember {
                mutableStateOf("")
            }
            var textD by remember {
                mutableStateOf("")
            }

            val sizeT = remember {
                mutableStateOf(20)
            }

            RegistroTheme(
                darkTheme = darkMode.value
            ) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //Greeting("Perro")
                    Row() {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Button(onClick = { darkMode.value = !darkMode.value }) {
                                Text(text = "Cambiar Tema")
                            }

                        }
                    }
                    Row(
                        modifier = Modifier.padding(20.dp,80.dp)
                    ) {
                        Column() {
                            Row() {
                                Text(
                                    text = "Período Académico: ",
                                    fontSize = sizeT.value.sp,
                                )
                                TextField(value = textPA,
                                    onValueChange = {textPA = it},
                                    textStyle = TextStyle(fontSize = sizeT.value.sp)
                                )

                            }
                            Row {
                                Text(text = "Escuela Profesional: ",
                                    fontSize = sizeT.value.sp
                                )
                                TextField(value = textEP,
                                    onValueChange = {textEP = it},
                                    textStyle = TextStyle(fontSize = sizeT.value.sp)
                                )
                            }
                            Row {
                                Text(text = "Código Asignatura: ",
                                    fontSize = sizeT.value.sp
                                )
                                TextField(value = textCA,
                                    onValueChange = {textCA = it},
                                    textStyle = TextStyle(fontSize = sizeT.value.sp)
                                )
                            }
                            Row {
                                Text(text = "Semestre: ",
                                    fontSize = sizeT.value.sp
                                )
                                TextField(value = textS,
                                    onValueChange = {textS = it},
                                            textStyle = TextStyle(fontSize = sizeT.value.sp)
                                )
                            }
                            Row {
                                Text(text = "Duración: ",
                                    fontSize = sizeT.value.sp
                                )
                                TextField(value = textD,
                                    onValueChange = {textD = it},
                                    textStyle = TextStyle(fontSize = sizeT.value.sp)
                                )
                            }
                            Row(){
                                Button(onClick = { sizeT.value = sizeT.value+1 }) {
                                    Text(text = "Aumentar Tamaño de Fuente")

                                }
                            }
                            Row(){
                                Button(onClick = { sizeT.value = sizeT.value-1 }) {
                                    Text(text = "Disminuir Tamaño de Fuente")

                                }
                            }
                            Row(){
                                Button(onClick = {
                                    lifecycleScope.launch{
                                        settingsManager.setDarkTheme(darkMode.value)
                                        settingsManager.setSizeF(sizeT.value)


                                    }
                                }) {
                                    Text(text = "Guardar Información")

                                }
                            }
                            Row(){
                                Button(onClick = {

                                    lifecycleScope.launch{
                                       settingsManager.d_theme.collect { col ->
                                           darkMode.value = col
                                       }
                                        settingsManager.sizeF.collect{size ->
                                            sizeT.value= size
                                        }

                                    }
                                }) {
                                    Text(text = "Mostrar Configuración guardada")

                                }
                            }

                        }
                    }

                }
            }
        }
    }

    private fun valueColor(): Boolean {
    var aux=false
        lifecycleScope.launch{
            settingsManager.d_theme.collect{
                aux = it
            }
        }
        return aux
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistroTheme {
        Greeting("Android")
    }
}