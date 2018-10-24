package com.rod.stateview.status

import android.content.Context
import android.support.annotation.NonNull
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.rod.library.SignalConstant
import com.rod.library.StatusView
import org.jetbrains.anko.*

/**
 *
 * @author Rod
 * @date 2018/10/24
 */
class LoadingStatusView : StatusView {

    private var mView: View? = null
    private var mReloadListener: StatusView.ClickToReloadListener? = null

    override fun setClickToReloadListener(listener: StatusView.ClickToReloadListener?) {
        mReloadListener = listener
    }

    override fun getView(@NonNull context: Context) = mView ?: with(context) {
        mView = frameLayout {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            verticalLayout {
                progressBar()
                textView("Loading") {
                    topPadding = 40
                }
            }.lparams(wrapContent, wrapContent, Gravity.CENTER)

        }
        mView
    }

    override fun handle(signal: Int) = signal == SignalConstant.LOADING
}