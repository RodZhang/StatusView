package com.rod.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rod
 * @date 2018/10/23
 */
public class StatusViewCenter {

    private final ViewGroup mParent;
    private final View mContentView;
    private final Context mContext;
    private final List<StatusView> mStatusViews;
    private int mContentIndex;
    private StatusView.ClickToReloadListener mClickToReloadListener;

    private StatusViewCenter(@NonNull Builder builder) {
        mParent = builder.mParent;
        mContext = mParent.getContext();
        mContentView = builder.mContentView;
        mStatusViews = builder.mStatusViews;
        mClickToReloadListener = builder.mClickToReloadListener;
        setup();
    }

    private void setup() {
        ContentView contentView = new ContentView(mContentView);
        mStatusViews.add(contentView);

        mContentIndex = mParent.indexOfChild(mContentView);
        mParent.removeView(mContentView);
        mParent.addView(mStatusViews.get(0).getView(mContext), mContentIndex);

        for (StatusView statusView : mStatusViews) {
            statusView.setClickToReloadListener(mClickToReloadListener);
        }
    }

    public void sendSignal(int signal) {
        for (StatusView statusItem : mStatusViews) {
            if (statusItem.handle(signal)) {
                mParent.removeViewAt(mContentIndex);
                mParent.addView(statusItem.getView(mContext), mContentIndex);
                return;
            }
        }
    }

    public static Builder builder(@NonNull ViewGroup parent, @NonNull View contentView) {
        return new Builder(parent, contentView);
    }

    public static class Builder {
        final ViewGroup mParent;
        final View mContentView;
        final List<StatusView> mStatusViews = new ArrayList<>();
        StatusView.ClickToReloadListener mClickToReloadListener;

        Builder(ViewGroup parent, View contentView) {
            mParent = parent;
            mContentView = contentView;
        }

        public Builder appendStatus(@NonNull StatusView statusView) {
            mStatusViews.add(statusView);
            return this;
        }

        public Builder setClickToReloadListener(StatusView.ClickToReloadListener listener) {
            mClickToReloadListener = listener;
            return this;
        }

        public StatusViewCenter build() {
            return new StatusViewCenter(this);
        }
    }
}
