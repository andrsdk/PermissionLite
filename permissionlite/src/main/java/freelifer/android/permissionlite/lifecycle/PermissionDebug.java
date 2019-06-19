package freelifer.android.permissionlite.lifecycle;

import android.util.Log;

/**
 * @author kzhu on 2019/6/18.
 */
public class PermissionDebug {

    private static final String TAG = "PermissionLite-v1.0";
    private static final boolean DBG = true;

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (DBG) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }
}
