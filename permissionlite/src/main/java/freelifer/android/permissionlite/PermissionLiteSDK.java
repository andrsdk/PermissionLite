package freelifer.android.permissionlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import freelifer.android.permissionlite.lifecycle.PermissionActivityLifecycle;

/**
 * @author zhukun on 2019/6/18.
 */
public class PermissionLiteSDK {

    private Context appContext;
    private PermissionActivityLifecycle lifecycle;
    public static PermissionLiteSDK getInstance() {
        return Holder.instance;
    }

    public Activity getTopActivity() {
        return lifecycle.getActivity();
    }

    public void autoInit(Application application) {
        this.appContext = application;

        if (lifecycle != null) {
            application.unregisterActivityLifecycleCallbacks(lifecycle);
        }
        lifecycle = new PermissionActivityLifecycle();
        application.registerActivityLifecycleCallbacks(lifecycle);
    }

    private static final class Holder {
        @SuppressLint("StaticFieldLeak")
        private static final PermissionLiteSDK instance = new PermissionLiteSDK();
    }

    private PermissionLiteSDK() {
    }
}
