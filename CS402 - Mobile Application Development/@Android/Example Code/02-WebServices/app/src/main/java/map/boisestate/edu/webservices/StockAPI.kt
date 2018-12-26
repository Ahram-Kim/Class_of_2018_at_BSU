package map.boisestate.edu.webservices

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by mziray on 1/30/18.
 */
interface StockAPI {

    @GET("/1.0/stock/aapl/previous")
    fun getStockPrice(): Call<StockPrice>
}