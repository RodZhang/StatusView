package com.rod.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Rod
 * @date 2018/10/24
 */
public interface StatusView {

    View getView(@NonNull Context context);

    boolean handle(int signal);
}
