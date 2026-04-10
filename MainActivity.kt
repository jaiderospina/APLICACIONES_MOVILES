package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    // Estado de la lista de películas
    private val movies = mutableStateListOf<Movie>()
    private var isLoading = mutableStateOf(true)
    private var errorMessage = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fetchMovies()
        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieScreen(
                        movies = movies,
                        isLoading = isLoading.value,
                        errorMessage = errorMessage.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRetrofit()
                    .create(MovieApiService::class.java)
                    .getPopularMovies()

                runOnUiThread {
                    if (response.isSuccessful) {
                        val result = response.body()?.movies ?: emptyList()
                        movies.clear()
                        movies.addAll(result)
                        isLoading.value = false
                    } else {
                        errorMessage.value = "Error al cargar las películas"
                        isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    errorMessage.value = "Sin conexión: ${e.message}"
                    isLoading.value = false
                }
            }
        }
    }
}

@Composable
fun MovieScreen(
    movies: List<Movie>,
    isLoading: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            // Cargando
            isLoading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color(0xFF7B5EA7))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Cargando películas...")
                }
            }

            // Error
            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }

            // Lista vacía
            movies.isEmpty() -> {
                Text("No se encontraron películas")
            }

            // Lista con datos
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(movies) { movie ->
                        MovieCard(movie = movie)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F0FA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "⭐ ${movie.rating}",
                fontSize = 14.sp,
                color = Color(0xFF7B5EA7),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}