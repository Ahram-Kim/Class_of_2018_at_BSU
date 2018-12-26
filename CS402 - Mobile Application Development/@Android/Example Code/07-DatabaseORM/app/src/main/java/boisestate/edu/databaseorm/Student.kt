package boisestate.edu.databaseorm

import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey

/**
 * Created by mziray on 3/1/18.
 */
class Student {

    @PrimaryKey
    @AutoIncrement
    var studentID:Int = 0

    var name:String? = null


    var teacherID:Teacher? = null

}