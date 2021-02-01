package studio.seno.singlelife.ui.user

import android.os.Bundle
import studio.seno.commonmodule.BaseActivity
import studio.seno.singlelife.R
import studio.seno.singlelife.ViewControlListener

class UserManageActivity : BaseActivity(), ViewControlListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_manage)
    }

    override fun finishCurrentActivity() {
        finish()
    }


}