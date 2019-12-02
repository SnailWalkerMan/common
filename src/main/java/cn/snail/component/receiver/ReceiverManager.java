package cn.snail.component.receiver;

import android.os.Build;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Autohor Yang Gang
 * @Date 2019/12/2
 * @Desc applied to ...
 */
public class ReceiverManager {

    private static final int CAPACITY = 4;
    private Map<String, BaseReceiver> mMaps;

    private ReceiverManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mMaps = new ArrayMap<>(CAPACITY);
        } else {
            mMaps = new HashMap<>(CAPACITY);
        }
    }

    public static ReceiverManager newSingleton() {
        return Holder.sINSTANCE;
    }

    public void register(String key, BaseReceiver receiver) {
        if (mMaps.containsKey(key)) {
            throw new AndroidRuntimeException("duplicate key!");
        }
        mMaps.put(key, receiver);
        receiver.register();
    }

    public void unRegister(String key) {
        BaseReceiver receiver = mMaps.get(key);
        if (receiver != null) {
            mMaps.remove(key);
            receiver.unRegister();
        }
    }

    private interface Holder {
        ReceiverManager sINSTANCE = new ReceiverManager();
    }
}
