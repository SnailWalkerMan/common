package cn.snail.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;

/**
 * @Autohor Yang Gang
 * @Date 2019/12/2
 * @Desc applied to ...
 */
public class BaseReceiver extends BroadcastReceiver {

    private Context mContext;

    public BaseReceiver(@NonNull Context context) {
        mContext = context;
    }

    public Intent register() {
        return register(getIntentFilter());
    }

    public Intent register(IntentFilter filter) {
        return mContext.registerReceiver(this, filter);
    }

    public void unRegister() {
        mContext.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    /**
     * 需要注册的action集合
     */
    protected String[] getActions() {
        return new String[0];
    }

    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        for (String it : getActions()) {
            intentFilter.addAction(it);
        }
        return intentFilter;
    }
}
