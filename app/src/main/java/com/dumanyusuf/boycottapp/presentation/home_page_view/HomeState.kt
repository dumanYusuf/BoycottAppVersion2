
import com.dumanyusuf.boycottapp.domain.model.Products

data class HomeState(
    val productList:List<Products> = emptyList(),
    val isLoading:Boolean=false,
    val isError:String=""
)