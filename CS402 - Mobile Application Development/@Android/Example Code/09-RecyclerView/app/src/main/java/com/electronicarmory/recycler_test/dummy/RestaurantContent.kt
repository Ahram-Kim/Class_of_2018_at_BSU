package com.electronicarmory.recycler_test.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object RestaurantContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<RestaurantItem> = ArrayList<RestaurantItem>()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, RestaurantItem> = HashMap<String, RestaurantItem>()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    fun addItem(item: RestaurantItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)

//        EventBus.getDefault.post(RestuarantEvent)
//        EventBus.getDefault.post(UpdateRestuarantEvent)
    }


    fun restaurants(): MutableList<RestaurantItem>{
        return ITEMS
    }


    private fun createDummyItem(position: Int): RestaurantItem {
        return RestaurantItem((position).toString(), "Restaurant " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    class RestaurantItem(val id: String, val content: String, val details: String) {

        public override fun toString(): String {
            return content
        }
    }
}
