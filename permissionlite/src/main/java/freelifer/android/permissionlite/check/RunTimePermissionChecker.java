package freelifer.android.permissionlite.check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * @author kzhu on 2019/6/18.
 */
public class RunTimePermissionChecker implements PermissionChecker {
    private String permission;

    private Context context;

    RunTimePermissionChecker(Context context, String permission) {
        this.permission = permission;
        this.context = context;
    }

    @Override
    public boolean check() {
        int checkResult = ContextCompat.checkSelfPermission(context, permission);
        return checkResult == PackageManager.PERMISSION_GRANTED;
    }
}
