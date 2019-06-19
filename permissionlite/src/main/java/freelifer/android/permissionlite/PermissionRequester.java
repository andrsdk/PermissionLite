package freelifer.android.permissionlite;

import android.app.Activity;

import freelifer.android.permissionlite.action.IPermissionAction;
import freelifer.android.permissionlite.action.PermissionFragmentFactory;
import freelifer.android.permissionlite.action.PermissionProxy;

/**
 * @author kzhu on 2019/6/18.
 */
public class PermissionRequester {

    private IPermissionAction action;
    private String[] permissions;

    public PermissionRequester(Activity activity) {
        this.action = new PermissionProxy(PermissionFragmentFactory.create(activity));
    }

    /**
     * 权限名称
     *
     * @param permissionNames 如 Manifest.permission.CAMERA
     *                        {@link android.Manifest.permission }
     */
    public PermissionRequester withPermission(String... permissionNames) {
        this.permissions = permissionNames;
        return this;
    }

    /**
     * 请求运行时权限
     */
    public void request(RequestPermissionListener listener) {
        if (action == null || permissions == null) {
            throw new IllegalArgumentException("fragment or params permission is null");
        }
        action.requestPermissions(permissions, listener);
    }
}
