package freelifer.android.permissionlite.check;


import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;

import java.lang.reflect.Method;

import app.PermissionDebug;

/**
 * @author kzhu on 2019/6/18.
 */
public class AppOpsChecker implements PermissionChecker {
    private Context context;
    private String permission;

    AppOpsChecker(Context context) {
        this(context, null);
    }

    AppOpsChecker(Context context, String permission) {
        this.context = context;
        this.permission = permission;
    }

    /**
     * 老的通過反射方式檢查權限狀態
     * 结果可能不准确，如果返回false一定未授予
     * 按需在里面添加
     * <p>
     * 如果没匹配上或者异常都默认权限授予
     *
     * @return 检查结果
     */

    @Override
    public boolean check() {
        if (null == permission) {
            return true;
        }
        switch (permission) {
            case Manifest.permission.READ_CONTACTS:
                return checkOp(4);
            case Manifest.permission.WRITE_CONTACTS:
                return checkOp(5);
            case Manifest.permission.CALL_PHONE:
                return checkOp(13);
            case Manifest.permission.READ_PHONE_STATE:
                return checkOp(51);
            case Manifest.permission.CAMERA:
                return checkOp(26);
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                return checkOp(59);
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                return checkOp(60);
            case Manifest.permission.ACCESS_FINE_LOCATION:
                return checkOp(1);
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                return checkOp(0);
            case Manifest.permission.RECORD_AUDIO:
                return checkOp(27);
            case Manifest.permission.BODY_SENSORS:
                return checkOp(56);
            case Manifest.permission.READ_CALENDAR:
                return checkOp(8);
            case Manifest.permission.WRITE_CALENDAR:
                return checkOp(9);
            case Manifest.permission.SEND_SMS:
                return checkOp(20);
            case Manifest.permission.READ_SMS:
                return checkOp(14);
            case Manifest.permission.RECEIVE_SMS:
                return checkOp(16);
            default:
                break;
        }
        return true;
    }

    private boolean checkOp(int op) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            PermissionDebug.d("4.4 below");
            return true;
        }
        try {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Method method = AppOpsManager.class.getDeclaredMethod("checkOp", int.class, int.class, String.class);
            return 0 == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
        } catch (Exception e) {
            PermissionDebug.w(e.toString());
            e.printStackTrace();
        }
        return true;
    }
}
