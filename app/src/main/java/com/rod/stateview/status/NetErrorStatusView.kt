package com.rod.stateview.status

import android.content.Context
import android.support.annotation.NonNull
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.rod.library.SignalConstant
import com.rod.library.StatusView
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

/**
 *
 * @author Rod
 * @date 2018/10/24
 */
class NetErrorStatusView : StatusView {

    private var mView : View? = null
    private var mReloadListener: StatusView.ClickToReloadListener? = null

    override fun setClickToReloadListener(listener: StatusView.ClickToReloadListener?) {
        mReloadListener = listener
    }

    override fun getView(@NonNull context: Context) = mView ?: with(context) {
        mView = frameLayout() {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            textView("Net error!").lparams(wrapContent, wrapContent, Gravity.CENTER)

            onClick { mReloadListener?.clickToReload(mView) }
        }
        mView
    }

    override fun getId() = SignalConstant.NET_ERROR
}