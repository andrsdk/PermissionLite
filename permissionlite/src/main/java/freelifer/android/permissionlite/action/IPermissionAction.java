package freelifer.android.permissionlite.action;

import freelifer.android.permissionlite.RequestPermissionListener;

/**
 * @author kzhu on 2019/6/18.
 */
public interface IPermissionAction {

    void requestPermissions(String[] permissions, RequestPermissionListener listener);
//    void requestSpecialPermission(Special permission, SpecialPermissionListener listener);
}
