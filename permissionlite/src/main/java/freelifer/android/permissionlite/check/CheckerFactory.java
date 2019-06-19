package freelifer.android.permissionlite.check;

import android.content.Context;

/**
 * @author kzhu on 2019/6/18.
 */
public class CheckerFactory {

    public static PermissionChecker create(Context context, String permission) {
//        if (PermissionTools.isOldPermissionSystem(context)) {
//            return new AppOpsChecker(context, permission);
//        } else {
            return new RunTimePermissionChecker(context, permission);
//        }
    }
}
