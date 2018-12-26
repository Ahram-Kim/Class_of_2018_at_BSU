package com.electronicarmory.recycler_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.electronicarmory.recycler_test.dummy.RestaurantContent

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val id = intent.getStringExtra("id")
        Log.d("BSU", id)

        val currentRestaurant = RestaurantContent.restaurants()[id.toInt()]

        val textView:TextView = findViewById(R.id.textView)
        textView.text = currentRestaurant.content
    }
}
