package com.rod.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
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
    private final SparseArray<StatusView> mStatusViews;
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
        mStatusViews.put(contentView.getId(), contentView);

        mContentIndex = mParent.indexOfChild(mContentView);
        mParent.removeView(mContentView);
        mParent.addView(mStatusViews.get(0).getView(mContext), mContentIndex);

        for (int i = 0, size = mStatusViews.size(); i < size; i++) {
            mStatusViews.valueAt(i).setClickToReloadListener(mClickToReloadListener);
        }
    }

    public void sendSignal(int signal) {
        StatusView statusView = mStatusViews.get(signal);
        if (statusView != null) {
            mParent.removeViewAt(mContentIndex);
            mParent.addView(statusView.getView(mContext), mContentIndex);
        }
    }

    public static Builder builder(@NonNull ViewGroup parent, @NonNull View contentView) {
        return new Builder(parent, contentView);
    }

    public static class Builder {
        final ViewGroup mParent;
        final View mContentView;
        final SparseArray<StatusView> mStatusViews = new SparseArray<>();
        StatusView.ClickToReloadListener mClickToReloadListener;

        Builder(ViewGroup parent, View contentView) {
            mParent = parent;
            mContentView = contentView;
        }

        public Builder putStatus(@NonNull StatusView statusView) {
            mStatusViews.put(statusView.getId(), statusView);
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
