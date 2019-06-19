package freelifer.android.permissionlite.action;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import freelifer.android.permissionlite.RequestPermissionListener;

/**
 * @author kzhu on 2019/6/18.
 */
public class PermissionSupportFragment extends Fragment implements IPermissionAction {

    private FragmentProxy proxy;

    public PermissionSupportFragment() {
        super();
        this.proxy = new FragmentProxy(this);
    }

    @Override
    public void requestPermissions(String[] permissions, RequestPermissionListener listener) {
        this.proxy.requestPermissions(permissions, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.proxy.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
