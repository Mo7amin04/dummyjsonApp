import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyjsonapp.Provider
import com.example.dummyjsonapp.pr.Product
import com.example.dummyjsonapp.pr.pro
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch

class ProviewM: ViewModel() {

    val client = Provider.client

    var postsList by mutableStateOf(emptyList<Product>())
        private set

    init {
        viewModelScope.launch {
            postsList = client.get("https://dummyjson.com/products").body<List<Product>>()
        }
    }

}