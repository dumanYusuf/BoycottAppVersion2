package com.dumanyusuf.boycottapp.presentation.category_page_view.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.dumanyusuf.boycottapp.R
import com.dumanyusuf.boycottapp.Screan
import com.dumanyusuf.boycottapp.presentation.category_page_view.CategoryViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun CategoryPageView(
    viewModel: CategoryViewModel= hiltViewModel(),
    navController: NavController,
    curentIndex:MutableState<Int>
) {

    LaunchedEffect(key1 = true) {
        viewModel.loadCategory()
    }

    BackHandler {
        curentIndex.value = 0
        navController.navigate(Screan.HomePageView.route) {
            popUpTo(Screan.HomePageView.route) { inclusive = true }
        }
    }

    val state=viewModel.state.collectAsState()
    val context= LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 50.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Kategoriler")
            Spacer(modifier = Modifier.padding(10.dp))

            if (state.value.isLoading) {
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
            else{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, bottom = 160.dp)
                ) {
                    items(state.value.categorytList) { categoryItem ->
                        Card(
                            modifier = Modifier
                                .clickable {
                                    val categoryObject = Gson().toJson(categoryItem)
                                    val encodedProductObject =
                                        URLEncoder.encode(categoryObject, "UTF-8")
                                    navController.navigate(Screan.CategoryFilterProductPage.route+"/$encodedProductObject")
                                }
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .shadow(4.dp, RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = categoryItem.categoryName,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Image(
                                    painter = rememberAsyncImagePainter(model = categoryItem.categoryImage, imageLoader = ImageLoader(context)),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.LightGray),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }


            }

        }
    }
}