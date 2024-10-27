import com.dumanyusuf.boycottapp.domain.model.Products

object ProductSingleton {

    // Tüm ürünleri saklamak için bir liste
    private var allProducts: List<Products> = emptyList()

    // Ürünleri yükleme ve saklama
    suspend fun loadAllProducts(apiCall: suspend () -> List<Products>) {
        if (allProducts.isEmpty()) {
            // Ürünleri yalnızca ilk defa boşsa yükler
            allProducts = apiCall()
        }
    }

    // Ürünleri alma
    fun getAllProducts(): List<Products> {
        return allProducts
    }

    // Ürünleri filtreleme
    fun getFilteredProducts(status: String): List<Products> {
        return allProducts.filter { it.productStatus.equals(status, ignoreCase = true) }
    }

    // Ürünleri arama
    fun searchProducts(query: String): List<Products> {
        return if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter { it.productName.contains(query, ignoreCase = true) }
        }
    }
}
