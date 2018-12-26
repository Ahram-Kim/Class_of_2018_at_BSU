package boisestate.edu.eventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.greenrobot.eventbus.EventBus
import java.util.*

class AddAssignmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_assignment)

        val newAssignment:Assignment = Assignment("Mobile Dev: CS402", Date())

        val addAssignmentEvent:AddAssignmentEvent = AddAssignmentEvent()
        addAssignmentEvent.assignment = newAssignment

        EventBus.getDefault().post(addAssignmentEvent)

        EventBus.getDefault().post(RemoveAssignmentEvent())
    }
}
