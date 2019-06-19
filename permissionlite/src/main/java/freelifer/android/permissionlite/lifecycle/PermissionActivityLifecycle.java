package freelifer.android.permissionlite.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhukun on 2019/6/18.
 */
public class PermissionActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private List<Activity> activities = new ArrayList<>();

    /**
     * 获取可用Activity
     *
     * @return Activity 优先栈顶
     */
    public Activity getActivity() {
        if (null == activities || activities.size() == 0) {
            return null;
        }
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (isActivityAvailable(activity)) {
                PermissionDebug.d("top available activity is :" + activity.getClass().getSimpleName());
                return activity;
            }
        }
        return null;
    }

    /**
     * 判断Activity 是否可用
     *
     * @param activity 目标Activity
     * @return true of false
     */
    public static boolean isActivityAvailable(Activity activity) {
        if (null == activity) {
            return false;
        }
        if (activity.isFinishing()) {
            PermissionDebug.d(" activity is finishing :" + activity.getClass().getSimpleName());
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            PermissionDebug.d(" activity is destroyed :" + activity.getClass().getSimpleName());
            return false;
        }
        return true;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}