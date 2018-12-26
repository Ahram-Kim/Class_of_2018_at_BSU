package boisestate.edu.databaseorm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition
import ninja.sakib.pultusorm.core.PultusORMQuery
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val appPath: String = applicationContext.filesDir.absolutePath

        val database:PultusORM = PultusORM("database.db", appPath )


//        val student:Student = Student()
//        student.name = "Mike"
//
//        database.save(student)


        val condition: PultusORMCondition = PultusORMCondition.Builder()
                .eq("studentID", 1)
//                .and()
//                .greaterEq("cgpa", 18)
//                .or()
//                .startsWith("name", "sami")
                .sort("name", PultusORMQuery.Sort.DESCENDING)
                .build()

        val students = database.find(Student(), condition)

        for( studentObject in students ){
            val currentStudent = studentObject as Student

            Log.d("BSU", currentStudent.name)
        }



        database.close()



    }
}
