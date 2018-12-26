package map.boisestate.edu.secondactivity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class PersonDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_display)


        val person:Person = intent.getSerializableExtra(MainActivity.PERSON_KEY) as Person
        val someInt:Int = intent.getIntExtra(MainActivity.SOMEINT_KEY, Int.MAX_VALUE)

        if( Int.MAX_VALUE == someInt ){
            Log.d("MZ", "No such someInt")
        }


        val firstNameTextView: TextView = findViewById(R.id.first_name_textview)
        firstNameTextView.text = person.firstName


        val saveButton:Button = findViewById(R.id.save_button)

        saveButton.setOnClickListener {

            setResult(Activity.RESULT_OK)

            finish()
        }

    }
}
