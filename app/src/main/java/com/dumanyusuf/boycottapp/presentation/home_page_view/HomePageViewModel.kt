package com.dumanyusuf.boycottapp.presentation.home_page_view

import HomeState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.boycottapp.domain.use_case.get_products_in_category_use_case.GetProductsInCategoryUseCase
import com.dumanyusuf.boycottapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getProductsInCategoryUseCase: GetProductsInCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState())
    val state: StateFlow<HomeState> = _state



     fun loadAllINProducts() {
         Log.e("veriler yuklendi1","veriler yuklendi1")
        viewModelScope.launch {
            _state.value = HomeState(isLoading = true)
            Log.e("veriler yuklendi2","veriler yuklendi2")
            // Ürünler yüklenmişse `ProductRepository`'den al
            if (ProductSingleton.getAllProducts().isNotEmpty()) {
                _state.value = HomeState(productList = ProductSingleton.getAllProducts())
                Log.e("veriler yuklendi3","veriler yuklendi3")
                return@launch
            }

            // Yüklenmemişse veritabanından yükleyip `ProductRepository`'e kaydet
            getProductsInCategoryUseCase.getProductInCategory().collect { resultProducts ->
                Log.e("veriler yuklendi4","veriler yuklendi4")
                when (resultProducts) {
                    is Resource.Success -> {
                        ProductSingleton.loadAllProducts { resultProducts.data ?: emptyList() }
                        _state.value = HomeState(productList = ProductSingleton.getAllProducts())
                        Log.e("veriler yuklendi5","veriler yuklendi5")
                    }
                    is Resource.Error -> {
                        _state.value = HomeState(isError = "error")
                    }
                    is Resource.Loading -> {
                        _state.value = HomeState(isLoading = true)
                    }
                }
            }
        }
    }

    // Filtreleme işlemi
    fun loadFilterProducts(status: String) {
        val filteredProducts = ProductSingleton.getFilteredProducts(status)
        _state.value = HomeState(productList = filteredProducts)
    }

    // Arama işlemi
    fun searchProducts(query: String) {
        val filteredProducts = ProductSingleton.searchProducts(query)
        _state.value = HomeState(productList = filteredProducts)
    }
}
