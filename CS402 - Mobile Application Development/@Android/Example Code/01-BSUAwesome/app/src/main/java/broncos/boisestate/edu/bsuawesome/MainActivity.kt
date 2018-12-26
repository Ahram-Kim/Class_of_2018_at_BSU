package broncos.boisestate.edu.bsuawesome

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val year:Int by lazy{
        2018
    }

    private lateinit var helloWorldTextView:TextView
    private var submitButton:Button? = null

    private val pi:Double = 3.1415


    private val someLazyInt: Int by lazy {
        val i = 19 + 10 // Delay some complex calculation
        i // this is what gets set to "someLazyInt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupUI()


        var someInt:Int = 5
        Log.d("BSU", someInt.toString())

        someInt = 10


        submitButton?.setOnClickListener {
            Log.d("BSU", "This button was tapped")

            val nameEditText:EditText = findViewById(R.id.editText)

//            helloWorldTextView.text = getString(R.string.hello_world) // sets hard coded
//            helloWorldTextView.text = "Hello ${nameEditText.text},  $someInt" // sets dynamic

            helloWorldTextView.text = String.format(getString(R.string.greeting), nameEditText.text, someInt)

        }

        Log.d("BSU", year.toString()) // gets set right here
        Log.d("BSU", someLazyInt.toString()) // gets set here
    }

    private fun setupUI(){
        helloWorldTextView = findViewById(R.id.helloworld_textview)
        submitButton = findViewById(R.id.submit_button)
    }
}
