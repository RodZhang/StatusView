package com.rod.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author Rod
 * @date 2018/10/24
 */
class ContentView implements StatusView {

    private final View mView;
    private StatusView.ClickToReloadListener mReloadListener;

    public ContentView(@NonNull View view) {
        mView = view;
    }

    @Override
    public View getView(@NonNull Context context) {
        return mView;
    }

    @Override
    public boolean handle(int signal) {
        return signal == SignalConstant.CONTENT;
    }

    @Override
    public void setClickToReloadListener(@Nullable ClickToReloadListener listener) {

    }
}
