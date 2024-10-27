package com.dumanyusuf.boycottapp.presentation.about_us_view

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class AboutViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val adUnitId = "ca-app-pub-3940256099942544/1033173712" // Test Interstitial ID
     var interstitialAd: InterstitialAd? = null

    fun loadInterstitialAd(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                interstitialAd = null
            }
        })
    }

    fun showInterstitialAd(context: Context, onAdClosed: () -> Unit) {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
                onAdClosed() // Reklam kapatılınca Chat sayfasına yönlendir
                loadInterstitialAd(context) // Reklamı tekrar yükle
            }
        }
        interstitialAd?.show(context as Activity)
    }
}
