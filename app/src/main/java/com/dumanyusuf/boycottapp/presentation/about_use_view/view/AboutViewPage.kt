package com.dumanyusuf.boycottapp.presentation.about_us_view.view


import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dumanyusuf.boycottapp.R
import com.dumanyusuf.boycottapp.Screan
import com.dumanyusuf.boycottapp.presentation.about_us_view.AboutViewModel
import com.dumanyusuf.boycottapp.presentation.component.CustomCard

@Composable
fun AboutPageView(
    navController: NavController,
    curentIndex: MutableState<Int>,
    viewModel: AboutViewModel= hiltViewModel()
) {
    val context = LocalContext.current

    // İlk reklam yüklemesi
    LaunchedEffect(Unit) {
        Log.e("test reklamı yüklendi","test reklamı yuklendi")
       viewModel.loadInterstitialAd(context)
    }

    BackHandler {
        curentIndex.value = 0
        navController.navigate(Screan.HomePageView.route) {
            popUpTo(Screan.HomePageView.route) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 50.dp)
    ) {
        Text(
            text = "Hesabım",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )

        // İlk Kart
        CustomCard(
            title = "Genel",
            onClick = { navController.navigate(Screan.AboutAppPageView.route) },
            imageRes = R.drawable.hakimizda,
            text = "Hakkımızda"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomCard(
            title = "İş Birliği",
            onClick = { navController.navigate(Screan.PartnerShipPageView.route) },
            imageRes = R.drawable.ortak,
            text = "İş Birliği"
        )

        // İkinci Kart
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "İletişim",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        CustomCard(
            title = "Öneri",
            onClick = { navController.navigate(Screan.SuggestionPageView.route) },
            imageRes = R.drawable.oneri,
            text = "Öneri"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomCard(
            title = "İtiraz",
            onClick = {
                navController.navigate(Screan.ObjectionPageView.route)
            },
            imageRes = R.drawable.itiraz,
            text = "İtiraz"
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomCard(
            title = "Bize Ulaş",
            onClick = { navController.navigate(Screan.ContactUsePageView.route) },
            imageRes = R.drawable.mailllll,
            text = "Bize Ulaş"
        )

        // ChatApp kartı
        CustomCard(
            title = "ChatApp",
            onClick = {
                // Reklam gösterme
                if (viewModel.interstitialAd != null) {
                    viewModel.showInterstitialAd(context) {
                        navController.navigate(Screan.GeminiPageView.route)
                    }
                } else {
                    navController.navigate(Screan.GeminiPageView.route)
                }
            },
            imageRes = R.drawable.gemini,
            text = "Chate Sor"
        )
    }
}
