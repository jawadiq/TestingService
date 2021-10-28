package com.example.ttttt

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.ttttt.Api.Constants
import com.example.ttttt.ui.theme.TTTTTTheme

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TTTTTTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MovieList(movieList = mainViewModel.movieListResponse)
                    mainViewModel.getMovieList()
                }
            }
        }
    }
}


@Composable
fun MovieList(movieList: List<Movie>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {

        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item, index, selectedIndex) { i ->
                selectedIndex = i
            }
        }
    }

}


@Composable
fun MovieItem(movie: Movie, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.background else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onClick(index) }
            .height(400.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = backgroundColor) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {


                    Image(
                        painter = rememberImagePainter(
                            data = Constants.IMAGE_URL+movie.posterPath,

                            builder = {
                                scale(coil.size.Scale.FILL)
                                placeholder(R.drawable.ic_launcher_background)


                            }
                        ),
                        contentDescription = movie.overview,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(300.dp),
                                contentScale = ContentScale.FillBounds,


                        )
                }
                Row(modifier = Modifier.fillMaxWidth()) {

Column() {


                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
//                            Text(
//                                text = movie.userId,
//                                style = MaterialTheme.typography.caption,
//                                modifier = Modifier
//                                    .background(
//                                        Color.LightGray
//                                    )
//                                    .padding(4.dp)
//                            )
                    Text(
                        text = movie.releaseDate,
                        style = MaterialTheme.typography.body1,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                }
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun MovieItem() {
//    val movie = Movie(
//        "Cocooo",
//        "https://howtodoandroid.com/images/coco.jpg",
//        "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
//        "Latest"
//    )
//
//    MovieItem(movie = movie, 0, 0) { i ->
//
//    }
//}