package com.dumanyusuf.boycottapp.presentation.category_page_view

import com.dumanyusuf.boycottapp.domain.model.Category

data class CategoryState(
    val categorytList:List<Category> = emptyList(),
    val isLoading:Boolean=false,
    val isError:String=""
)