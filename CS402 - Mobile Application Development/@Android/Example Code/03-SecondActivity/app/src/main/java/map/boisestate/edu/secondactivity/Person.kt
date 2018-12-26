package map.boisestate.edu.secondactivity

import java.io.Serializable
import java.util.*

/**
 * Created by mziray on 2/8/18.
 */
data class Person(val firstName:String,
                  val lastName:String,
                  val age:Int,
                  val birthday:Date):Serializable {
}