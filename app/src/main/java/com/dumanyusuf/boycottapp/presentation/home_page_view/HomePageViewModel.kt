package com.dumanyusuf.boycottapp.presentation.home_page_view

import HomeState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.boycottapp.domain.model.Products
import com.dumanyusuf.boycottapp.domain.use_case.get_filter_products_use_case.GetFilterProductsUseCase
import com.dumanyusuf.boycottapp.domain.use_case.get_products_in_category_use_case.GetProductsInCategoryUseCase
import com.dumanyusuf.boycottapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getFilterProductsUseCase: GetFilterProductsUseCase,
    private val getProductsInCategoryUseCase: GetProductsInCategoryUseCase
):ViewModel() {


     private val _state= MutableStateFlow<HomeState>(HomeState())
     val state:StateFlow<HomeState> = _state

      var allProducts: List<Products> = emptyList()

    fun loadAllINProducts(){
        viewModelScope.launch {
             _state.value=HomeState(isLoading = true)
             getProductsInCategoryUseCase.getProductInCategory().collect{resultProducts->
                 when(resultProducts){
                     is Resource.Success->{
                         allProducts = resultProducts.data ?: emptyList()
                         _state.value=HomeState(productList = resultProducts.data?: emptyList())
                         Log.e("success products in category","success get products:${resultProducts.data}")
                     }
                     is Resource.Error->{
                         _state.value=HomeState(isError = "errror")
                         Log.e("erorr products in category","error get products:${resultProducts.message}")
                     }
                     is Resource.Loading->{
                         _state.value=HomeState(isLoading = true)
                         Log.e("loading products in category","loading get products")
                     }
                 }
             }
         }
     }

    fun loadFilterProducts(status:String){
        viewModelScope.launch {
            _state.value=HomeState(isLoading = true)
            getFilterProductsUseCase.getFilterProductsUseCase(status).collect{resultProducts->
                when(resultProducts){
                    is Resource.Success->{
                        _state.value=HomeState(productList = resultProducts.data?: emptyList())
                        Log.e("success products","success get products:${resultProducts.data}")
                    }
                    is Resource.Error->{
                        _state.value=HomeState(isError = "errror")
                        Log.e("erorr products","error get products:${resultProducts.message}")
                    }
                    is Resource.Loading->{
                        _state.value=HomeState(isLoading = true)
                        Log.e("loading products","loading get products")
                    }
                }
            }
        }
    }


    fun searchProducts(query: String) {
        if (query.isEmpty()) {
            _state.value = HomeState(productList = allProducts) // Boşsa tüm ürünleri göster
            return
        }

        val filteredProducts = allProducts.filter { product ->
            product.productName.contains(query, ignoreCase = true) // Ürün adını kontrol et
        }

        _state.value = HomeState(productList = filteredProducts)
    }

}