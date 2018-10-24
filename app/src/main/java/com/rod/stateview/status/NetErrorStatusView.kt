package com.rod.stateview.status

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.rod.library.SignalConstant
import com.rod.library.StatusView
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

/**
 *
 * @author Rod
 * @date 2018/10/24
 */
class NetErrorStatusView : StatusView {

    private var mView : View? = null

    override fun getView(context: Context) = mView ?: with(context) {
        mView = frameLayout() {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            textView("Net error!").lparams(wrapContent, wrapContent, Gravity.CENTER)
        }
        mView
    }

    override fun handle(signal: Int) = signal == SignalConstant.NET_ERROR
}