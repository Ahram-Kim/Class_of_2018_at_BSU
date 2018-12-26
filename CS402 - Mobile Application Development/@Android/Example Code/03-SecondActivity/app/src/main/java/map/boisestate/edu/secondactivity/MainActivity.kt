package map.boisestate.edu.secondactivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERSON_KEY:String = "person"
        const val SOMEINT_KEY:String = "someInt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNameEditText:EditText = findViewById(R.id.first_name_edittext)
        val lastNameEditText:EditText = findViewById(R.id.last_name_edittext)
        val ageEditText:EditText = findViewById(R.id.age_edittext)

//        val birthdayEditText:EditText = findViewById(R.id.first_name_edittext)
        val displayButton:Button = findViewById(R.id.display_button)

        displayButton.setOnClickListener {


            val person:Person = Person(firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    ageEditText.text.toString().toInt(),
                    Date())

            val intent:Intent = Intent(this, PersonDisplayActivity::class.java)

            intent.putExtra(PERSON_KEY, person)
            intent.putExtra(SOMEINT_KEY, 10)


//            startActivity(intent)
            startActivityForResult(intent, 888)

        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( resultCode == Activity.RESULT_OK ){
            Log.d("MZ", "RESULT OK!!!")
        }
        else{
            Log.d("MZ", "USER CANCELLED :(")
        }




    }




}
