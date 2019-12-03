package cn.snail.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AndroidRuntimeException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.snail.component.contract.IResultListener;

/**
 * @Autohor Yang Gang
 * @Date 2019/12/2
 * @Desc applied to ...
 */
public class BaseReceiver extends BroadcastReceiver {

    private Context mContext;
    private IResultListener<Intent> mListener;

    public BaseReceiver(@NonNull Context context) {
        mContext = context;
    }

    public BaseReceiver(@NonNull Context context, @Nullable IResultListener<Intent> listener) {
        mContext = context;
        mListener = listener;
    }

    public Intent register() {
        return register(getIntentFilter());
    }

    public Intent register(@NonNull IntentFilter filter) {
        return mContext.registerReceiver(this, filter);
    }

    public void unRegister() {
        mContext.unregisterReceiver(this);
        mListener = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mListener != null) {
            mListener.onResult(intent);
        }
    }

    /**
     * 需要注册的action集合
     */
    @NonNull
    protected String[] getActions() {
        return new String[0];
    }

    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        String[] actions = getActions();
        if (actions.length == 0) {
            throw new AndroidRuntimeException("actions should not be ignored!");
        }
        for (String it : actions) {
            intentFilter.addAction(it);
        }
        return intentFilter;
    }
}
