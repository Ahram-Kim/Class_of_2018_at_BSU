package map.boisestate.edu.webservices

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by mziray on 1/30/18.
 */
class StockPriceService {

    private val service: StockAPI

    init{
        val retrofit = Retrofit.Builder().
                baseUrl("https://api.iextrading.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build()
        service = retrofit.create(StockAPI::class.java)
    }

    fun getStockPrice(callback: Callback<StockPrice>){
        val call = service.getStockPrice()
        call.enqueue(callback)
    }
}