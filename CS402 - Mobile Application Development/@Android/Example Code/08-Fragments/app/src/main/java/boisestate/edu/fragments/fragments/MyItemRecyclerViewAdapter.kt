package achdreporter.achdidaho.org.fragments.fragments

import achdreporter.achdidaho.org.fragments.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import achdreporter.achdidaho.org.fragments.fragments.ItemFragment.OnListFragmentInteractionListener
import achdreporter.achdidaho.org.fragments.fragments.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
 class MyItemRecyclerViewAdapter(private val mValues:List<DummyItem>, private val mListener:OnListFragmentInteractionListener?):RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

public override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder {
val view = LayoutInflater.from(parent.getContext())
.inflate(R.layout.fragment_item, parent, false)
return ViewHolder(view)
}

public override fun onBindViewHolder(holder:ViewHolder, position:Int) {
holder.mItem = mValues.get(position)
holder.mIdView.setText(mValues.get(position).id)
holder.mContentView.setText(mValues.get(position).content)

holder.mView.setOnClickListener(object:View.OnClickListener {
public override fun onClick(v:View) {
if (null != mListener)
{
 // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener!!.onListFragmentInteraction(holder.mItem!!)
}
}
})
}

public override fun getItemCount():Int {
return mValues.size
}

inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {
 val mIdView:TextView
 val mContentView:TextView
 var mItem:DummyItem? = null

init{
mIdView = mView.findViewById(R.id.id) as TextView
mContentView = mView.findViewById(R.id.content) as TextView
}

public override fun toString():String {
return super.toString() + " '" + mContentView.getText() + "'"
}
}
}
