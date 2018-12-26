package map.boisestate.edu.webservices

import java.io.Serializable

/**
 * Created by mziray on 1/30/18.
 */
data class StockPrice( val symbol:String,
                       val date:String,
                       val open:Float,
                       val close:String):Serializable {
}