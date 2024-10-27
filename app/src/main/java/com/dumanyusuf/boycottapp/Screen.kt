package com.dumanyusuf.boycottapp

sealed class Screan(val route:String) {

    object HomePageView:Screan("home_page")
    object CategoryPageView:Screan("category_page")
    object NewsPageView:Screan("news_page")
    object AboutPageView:Screan("about_page")
    object ProductDetailPageView:Screan("detail_page")
    object CategoryFilterProductPage:Screan("category_filter_product_page")
    object AboutAppPageView:Screan("about__app_page")
    object PartnerShipPageView:Screan("partner_ship_page")
    object DonationPageView:Screan("donation_page")
    object SuggestionPageView:Screan("suggestion_page")
    object ObjectionPageView:Screan("objection_page")
    object ContactUsePageView:Screan("contact_page")
    object SplashScreen:Screan("splash_screan")
    object NewsDetailPage:Screan("news_detail_page")
    object GeminiPageView:Screan("gemini_page")











}