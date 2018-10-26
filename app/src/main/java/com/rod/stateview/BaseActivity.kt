package com.rod.stateview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rod.library.SignalConstant
import com.rod.library.StatusViewCenter
import com.rod.stateview.status.EmptyContentStatusView
import com.rod.stateview.status.LoadingStatusView
import com.rod.stateview.status.NetErrorStatusView

/**
 *
 * @author Rod
 * @date 2018/10/26
 */
abstract class BaseActivity: AppCompatActivity() {
    private lateinit var mStatusCenter: StatusViewCenter
    private val mUIHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getContentLayoutId())

        val builder = StatusViewCenter.builder(getContentContainer(), getContentView())
                .putStatus(LoadingStatusView())
                .putStatus(NetErrorStatusView())
                .putStatus(EmptyContentStatusView(View.OnClickListener {
                    mStatusCenter.sendSignal(SignalConstant.LOADING)
                    mUIHandler.postDelayed({ mStatusCenter.sendSignal(SignalConstant.NET_ERROR) }, 1000)
                }))
                .setClickToReloadListener {
                    Toast.makeText(this, "Reload clicked", Toast.LENGTH_SHORT).show()

                    mStatusCenter.sendSignal(SignalConstant.LOADING)
                    mUIHandler.postDelayed({ mStatusCenter.sendSignal(SignalConstant.CONTENT) }, 1000)
                }
        setupStatusCenter(builder)

        mStatusCenter = builder.build()
        mStatusCenter.sendSignal(SignalConstant.LOADING)

        mUIHandler.postDelayed({ mStatusCenter.sendSignal(SignalConstant.CONTENT_EMPTY) }, 3000)
    }

    open fun setupStatusCenter(builder: StatusViewCenter.Builder) {

    }

    abstract fun getContentLayoutId(): Int
    abstract fun getContentContainer(): ViewGroup
    abstract fun getContentView(): View
    abstract fun setupView();
}