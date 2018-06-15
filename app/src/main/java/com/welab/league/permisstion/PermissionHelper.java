package com.welab.league.permisstion;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by ljeongseok on 2018. 4. 17..
 */

public class PermissionHelper {
    private final int REQUEST_PERMISSION = 0;

    private PermissionType mPermissionType;
    private OnPermissionListener mOnPermissionListener;

    private Activity mActivity;

    public PermissionHelper(Activity activity, PermissionType permissionType) {
        mPermissionType = permissionType;
        mActivity = activity;
    }

    public void setPermisstionListener(OnPermissionListener onPermissionListener) {
        mOnPermissionListener = onPermissionListener;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean granted = true;

                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            granted = false;
                            break;
                        }
                    }

                    if (mOnPermissionListener != null) {
                        if (granted == true) {
                            mOnPermissionListener.onPermissionGranted();
                        } else {
                            mOnPermissionListener.onPermisstionDenied();
                        }
                    } else {
                        if (granted == false) {
                            mActivity.finish();
                        }
                    }
                }
                break;
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> noGrantPermisstionList = new ArrayList<>();

            for (String permisstion : mPermissionType.getPermissionList()) {
                if (ContextCompat.checkSelfPermission(mActivity, permisstion) == PackageManager.PERMISSION_DENIED) {
                    noGrantPermisstionList.add(permisstion);
                }
            }

            if (noGrantPermisstionList.size() > 0) {
                ActivityCompat.requestPermissions(mActivity, noGrantPermisstionList.toArray(new String[0]), REQUEST_PERMISSION);
            }
        }
    }

}
