package com.example.dummyjsonapp.Screens

import SharedPreferencesHelper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
data class Product(val id: Int, val title: String, val price: Double, val description: String)
@Composable
fun Page_Api(navController: NavController) {
    val client = OkHttpClient()
    val context = LocalContext.current
    val preferencesHelper = remember { SharedPreferencesHelper(context) }
    val token = preferencesHelper.getString("token")
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        // Step 1: Fetch token
        scope.launch(Dispatchers.IO) {
           // val token = fetchToken(client)
            // Step 2: Fetch products using token
            if (token != null) {
                products = fetchProducts(client, token)
                isLoading = false
            }
        }
    }
    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        ProductList(products)
    }


//    Scaffold(floatingActionButton = { FloatingActionButton(onClick = {
//        products= fetchProducts(client,q)
//    }){} }
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//        ) {
//            ProductList(products)
//
//        }
//    }


}

// Function to fetch the products using the token
fun fetchProducts(client: OkHttpClient, token: String): List<Product> {
    val productsUrl = "https://dummyjson.com/auth/products"
    val request = Request.Builder()
        .url(productsUrl)
        .addHeader("Authorization", "Bearer $token")  // Adding the token to Authorization header
        .build()

    return try {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            // Parse the response as a JSON object
            val responseBody = response.body?.string() ?: return emptyList()

            // Parse the JSON response
            val json = JSONObject(responseBody)

            // Check if "products" key exists
            if (json.has("products")) {
                val productsArray = json.getJSONArray("products")

                // Convert JSON array to a list of Product objects
                List(productsArray.length()) { index ->
                    val productJson = productsArray.getJSONObject(index)
                    Product(
                        id = productJson.getInt("id"),
                        title = productJson.getString("title"),
                        price = productJson.getDouble("price"),
                        description = productJson.getString("description")
                    )
                }
            } else {
                emptyList()  // Return an empty list if "products" key doesn't exist
            }
        } else {
            emptyList()  // Handle unsuccessful response
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()  // Handle exceptions and return an empty list in case of error
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Price: $${product.price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = product.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}


//fun fetchProducts(client: OkHttpClient, token: String) {
//    val productsUrl = "https://dummyjson.com/auth/products"
//
//    val productsRequest = Request.Builder()
//        .url(productsUrl)
//        .addHeader("Authorization", "Bearer $token")  // Add the token to the Authorization header
//        .build()
//
//    client.newCall(productsRequest).enqueue(object : Callback {
//        override fun onFailure(call: Call, e: IOException) {
//            e.printStackTrace()
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            response.use {
//                if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//                // Parse and print the products response
//                val responseBody = response.body?.string()
//                println("Products: $responseBody")
//            }
//        }
//    })
//}

