package com.example.dummyjsonapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dummyjsonapp.R
import com.example.dummyjsonapp.routrs

@Composable
fun Register_Screen(navController: NavController) {


    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var name by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var phone by remember {
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.register),
            contentDescription = "",
            Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            Modifier.padding(end = 160.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Register",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF23395B)
            )
            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "Please register to login.", fontSize = 16.sp, fontWeight = FontWeight.Bold,
                color = Color(0xFF23395B)
            )
        }
        Box(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0)
            ),
            shape = CircleShape,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 20.sp),
            value = name,
            onValueChange = {
                name = it
            },
            prefix = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")


            },
            label = {
                Text(text = "username")
            },

            modifier = Modifier
                .width(360.dp)
        )
        Spacer(modifier = Modifier.height(17.dp))
        OutlinedTextField(
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0)
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 20.sp),
            prefix = {
                Icon(imageVector = Icons.Default.Call, contentDescription = "")
            },
            label = {
                Text(text = "Mobile Number")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = phone,
            onValueChange = { phone = it },

            modifier = Modifier
                .width(360.dp)
            //    .border(0.dp, Color.Gray),
            //.size(width = , height = 55.dp),
        )
        Spacer(modifier = Modifier.height(17.dp))

        OutlinedTextField(
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0)
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = 20.sp),
            prefix = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "")
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Face, contentDescription = "")
                }
            },
            label = {
                Text(text = "password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password,
            onValueChange = { password = it },

            modifier = Modifier
                .width(360.dp)
        )
        Box(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
                  //  navController.navigate("${routrs.HOMEPAGE}/$name")

                }
            },

            modifier = Modifier
                .size(width = 360.dp, height = 55.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23395B)
            )

        ) {
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp)
            )


        }
        Row {
            Text(
                text = "Already have account? ",
                fontSize = 15.sp,
                fontWeight = FontWeight.W900,
                color = Color(0xFFF0F0F0),
                modifier = Modifier.padding(top = 16.dp)
            )
            TextButton(onClick = {
                navController.popBackStack()


            }) {
                Text(
                    text = "Sign In",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF23395B)
                )
            }
        }


    }


}