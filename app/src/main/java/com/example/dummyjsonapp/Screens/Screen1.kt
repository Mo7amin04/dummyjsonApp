import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dummyjsonapp.R
import com.example.dummyjsonapp.routrs
import com.example.dummyjsonapp.ui.theme.Shadow5
import com.example.dummyjsonapp.ui.theme.Yellow800
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


@Composable
fun Login_Screen(navController: NavController) {


    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFFFFF)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            val context = LocalContext.current
            val sharedPreferences = context.getSharedPreferences("my_prefs", MODE_PRIVATE)
            var name by remember {
                mutableStateOf("")
            }
            val preferencesHelper = remember { SharedPreferencesHelper(context) }
            var showMessage by remember { mutableStateOf(false) }
            var isPassword by remember {
                mutableStateOf(true)
            }
            var password by remember {
                mutableStateOf("")
            }
            Image(
                painter = painterResource(id = R.drawable.photo),
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
                    text = "Login",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF23395B)
                )
                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "Sign in to your account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
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

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

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
            Box(modifier = Modifier.height(17.dp))


            OutlinedTextField(
                visualTransformation = if (isPassword) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }, shape = CircleShape,
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
                    IconButton(onClick = {

                        isPassword = !isPassword


                    }) {
                        if (isPassword) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "أيقونة مفضلة ممتدة"
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "أيقونة مفضلة ممتدة"
                            )
                        }

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
            TextButton(
                onClick = { },
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 20.dp)
            ) {
                Text(text = "Forget Password?", color = Color(0xFF23395B))
            }
            Box(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.isEmpty() || password.isEmpty()) {
                        showMessage = true
                    } else {

                        val client = OkHttpClient()


                        val jsonBody = JSONObject().apply {
                            put("username", name)
                            put("password", password)
                        }

                        val requestBody = jsonBody.toString()
                            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

                        var request = Request.Builder()
                            .url("https://dummyjson.com/auth/login")
                            .post(requestBody)
                            .build()

                        CoroutineScope(Dispatchers.IO).launch {
                            client.newCall(request).enqueue(object : Callback {
                                override fun onFailure(call: Call, e: IOException) {
                                    e.printStackTrace()
                                }

                                override fun onResponse(call: Call, response: Response) {
                                    if (response.isSuccessful) {
                                        response.body?.let { responseBody ->
                                            val jsonResponse = JSONObject(responseBody.string())
                                            val token = jsonResponse.getString("token")
                                            preferencesHelper.saveString("token", token)
                                            println(preferencesHelper.getString("token"))
                                        }


                                    } else {
                                        println("Request failed: ${response.code}")
                                    }
                                }
                            })
                        }
                    }
                    navController.navigate(routrs.HOMEPAGE)


                },
                modifier = Modifier
                    .size(width = 360.dp, height = 55.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow800
                )

            ) {
                Text(
                    text = "Sign in",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp)
                )


            }
            MessageSnackbar(
                message = "هناك خطأ!",
                visible = showMessage,
                onDismiss = { showMessage = false }
            )
            Row {
                Text(
                    text = "Don`t have an account? ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W900,
                    color = Color(0xFF23395B),
                    modifier = Modifier.padding(top = 12.dp)
                )
                TextButton(onClick = {
                    navController.navigate(routrs.REGISTER)


                }) {
                    Text(
                        text = "Register here",
                        fontWeight = FontWeight.Bold,
                        color = Shadow5
                    )
                }
            }


        }
    }
}

suspend fun simulateLoginProcess(loginState: MutableState<Boolean>) {
    delay(2000) // Simulating network request or login delay
    loginState.value = true // Set login state to true after the delay
}

@Composable
fun MessageSnackbar(
    message: String,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    if (visible) {
        // Show Snackbar-like message at the bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Red, shape = RoundedCornerShape(8.dp))
                //.align(Alignment.BottomCenter)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = Color.White,
                style = TextStyle(fontSize = 16.sp)
            )
        }
        LaunchedEffect(Unit) {
            delay(2000) // Duration for the message
            onDismiss()
        }
    }
}

suspend fun getUserInfo(token: String, client: OkHttpClient): JSONObject? {
    return withContext(Dispatchers.IO) {
        try {
            // إنشاء الطلب (Request) مع إضافة التوكن في الهيدر
            val request = Request.Builder()
                .url("https://dummyjson.com/auth/users/")
                .addHeader("Authorization", "Bearer $token")
                .build()

            // إرسال الطلب
            val response = client.newCall(request).execute()

            // التحقق من نجاح العملية
            if (response.isSuccessful) {
                // الحصول على الاستجابة (response body)
                val responseBody = response.body?.string()
                return@withContext JSONObject(responseBody)
            } else {
                Log.e("UserInfoError", "Error: ${response.message}")
            }
        } catch (e: IOException) {
            Log.e("UserInfoException", "Exception: ${e.message}")
        }
        return@withContext null
    }
}

// استدعاء الدالة
suspend fun fetchAndPrintUserInfo(token: String, client: OkHttpClient) {
    val userInfo = getUserInfo(token, client)
    userInfo?.let {
        // طباعة البيانات
        Log.d("UserInfo", "User Info: $userInfo")
    }
}

