package map.boisestate.edu.webservices

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var currentPrice:StockPrice
    lateinit var priceTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        priceTextView = findViewById(R.id.price_textview)

        val downloader:StockPriceService = StockPriceService()
        val callback = object: Callback<StockPrice>{
            override fun onFailure(call: Call<StockPrice>?, t: Throwable?) {
                Log.e("MainActivity", "Problems", t)
            }

            override fun onResponse(call: Call<StockPrice>?, response: Response<StockPrice>?) {
                response?.isSuccessful.let{
                    Log.d("Response", response.toString())
                    Log.d("Response", response?.body().toString())
                    this@MainActivity.currentPrice = response?.body()!!

                    priceTextView.text = this@MainActivity.currentPrice.open.toString()
                }
            }

        }
        downloader.getStockPrice(callback)
    }
}
