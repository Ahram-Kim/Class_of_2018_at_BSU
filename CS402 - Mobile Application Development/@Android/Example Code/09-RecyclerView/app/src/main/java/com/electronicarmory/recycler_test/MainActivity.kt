package com.electronicarmory.recycler_test

import android.app.FragmentTransaction
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.electronicarmory.recycler_test.dummy.RestaurantContent

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
        RestaurantItemFragment.OnListFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener {


    private lateinit var fragmentContainer:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        fragmentContainer = findViewById(R.id.fragmentContainer)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListFragmentInteraction(item: RestaurantContent.RestaurantItem) {
        Log.d("BSU", "${item.id}: ${item.content}")

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment2, BlankFragment.newInstance("${item.id}", "hello world"))
                .addToBackStack(fragmentContainer.toString())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()

// Uses an activity instead
//        val intent = Intent(this, Main2Activity::class.java)
//        intent.putExtra("id", item.id)
//        startActivity(intent)
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("BSU", "blank fragment was interacted with")
    }


}
