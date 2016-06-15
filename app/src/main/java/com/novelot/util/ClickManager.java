package com.novelot.util;

import android.os.Message;
import android.view.ViewConfiguration;

/**
 * 解决多个点击事件
 * Created by 刘云龙 on 2016/6/14.
 */
public class ClickManager {
    private static ClickManager mInstance;
    private Message mMessage;

    private ClickManager() {
    }

    public static ClickManager getIntance() {
        if (mInstance == null) {
            synchronized (ClickManager.class) {
                if (mInstance == null) {
                    mInstance = new ClickManager();
                }
            }
        }

        return mInstance;
    }

    public void add(Message m) {
        if (mMessage != null && mMessage.getTarget() != null) {
            mMessage.getTarget().removeCallbacks(mMessage.getCallback());
        }
        mMessage = m;
        if (mMessage != null && mMessage.getTarget() != null) {
            mMessage.getTarget().sendMessageDelayed(mMessage, ViewConfiguration.getTapTimeout() / 2);
        }
    }
}
