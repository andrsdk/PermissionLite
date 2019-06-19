package freelifer.android.permissionlite.action;

import freelifer.android.permissionlite.RequestPermissionListener;
import freelifer.android.permissionlite.action.IPermissionAction;

/**
 * @author kzhu on 2019/6/18.
 */
public class PermissionProxy implements IPermissionAction {
    private IPermissionAction action;

    public PermissionProxy(IPermissionAction action) {
        this.action = action;
    }

    @Override
    public void requestPermissions(String[] permissions, RequestPermissionListener listener) {
        this.action.requestPermissions(permissions, listener);
    }
}
