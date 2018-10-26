package com.rod.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author Rod
 * @date 2018/10/24
 */
public interface StatusView {

    View getView(@NonNull Context context);

    int getId();

    void setClickToReloadListener(@Nullable ClickToReloadListener listener);

    interface ClickToReloadListener {
        void clickToReload(View view);
    }
}
