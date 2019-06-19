package freelifer.android.permissionlite.action;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import freelifer.android.permissionlite.Permission;
import freelifer.android.permissionlite.lifecycle.PermissionDebug;
import freelifer.android.permissionlite.RequestPermissionListener;

/**
 * @author kzhu on 2019/6/18.
 */
public class FragmentProxy {

    private static final int REQUEST_CODE_PERMISSION = 0x1000;
    private RequestPermissionListener requestPermissionListener;

    private Object target;

    public FragmentProxy(Object target) {
        this.target = target;
    }

    public void requestPermissions(String[] permissions, RequestPermissionListener listener) {
        this.requestPermissionListener = listener;
        requestPermissions(permissions);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permission[] permissionResults = new Permission[permissions.length];
        if (requestCode == REQUEST_CODE_PERMISSION) {
            for (int i = 0; i < permissions.length; ++i) {
                Permission permission = new Permission(permissions[i], grantResults[i], this.shouldShowRequestPermissionRationale(permissions[i]));
                permissionResults[i] = permission;
            }
        }
//        if (null != requestPermissionListener && PermissionTools.isActivityAvailable(getActivity())) {
//            requestPermissionListener.onPermissionResult(permissionResults);
//        }
    }

    private void getActivity() {
        if (target instanceof android.app.Fragment) {
            ((android.app.Fragment) target).getActivity();
        } else if (target instanceof Fragment) {
            ((Fragment) target).getActivity();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissions(String[] permissions) {
        if (target instanceof android.app.Fragment) {
            ((android.app.Fragment) target).requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        } else if (target instanceof Fragment) {
            ((Fragment) target).requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        } else {
            PermissionDebug.e("Other Fragment requestPermissions");
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean shouldShowRequestPermissionRationale(String permission) {
        if (target instanceof android.app.Fragment) {
            return ((android.app.Fragment) target).shouldShowRequestPermissionRationale(permission);
        } else if (target instanceof Fragment) {
            return ((Fragment) target).shouldShowRequestPermissionRationale(permission);
        } else {
            PermissionDebug.e("Other Fragment shouldShowRequestPermissionRationale");
            return false;
        }
    }
}
