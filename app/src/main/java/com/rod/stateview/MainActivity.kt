package com.rod.stateview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rod.library.SignalConstant
import com.rod.library.StatusViewCenter
import com.rod.stateview.status.EmptyContentStatusView
import com.rod.stateview.status.LoadingStatusView
import com.rod.stateview.status.NetErrorStatusView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mStatusCenter: StatusViewCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStatusCenter = StatusViewCenter.builder(container, content)
                .appendStatus(LoadingStatusView())
                .appendStatus(NetErrorStatusView())
                .appendStatus(EmptyContentStatusView())
                .build()

        mStatusCenter.setup()
        mStatusCenter.sendSignal(SignalConstant.LOADING)

        container.postDelayed({ mStatusCenter.sendSignal(SignalConstant.CONTENT_EMPTY) }, 3000)
    }
}
