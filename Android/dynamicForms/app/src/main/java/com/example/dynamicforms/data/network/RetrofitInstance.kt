import com.example.dynamicforms.data.network.interfaces.FormInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://dynamic-forms-b4f58-default-rtdb.firebaseio.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: FormInterface by lazy {
        retrofit.create(FormInterface::class.java)
    }
}