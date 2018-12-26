package boisestate.edu.eventbus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newIntent:Intent = Intent(this, AddAssignmentActivity::class.java)

        startActivity(newIntent)
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: AddAssignmentEvent) {
        Log.d("BSU", "Assignment was added!!!")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: RemoveAssignmentEvent) {
        Log.d("BSU", "Assignment was removed!")
    }



    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
