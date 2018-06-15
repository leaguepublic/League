package com.welab.league.permisstion;

import android.Manifest;

import java.util.ArrayList;

/**
 * Created by ljeongseok on 2018. 4. 17..
 */

public class PermissionType {

    private ArrayList<String> mPermissionList;

    private PermissionType(Builder builder) {
        mPermissionList = builder.mPermissionList;
    }

    public static class Builder {
        private ArrayList<String> mPermissionList = new ArrayList<>();

        public Builder setLocation() {
            mPermissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            mPermissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

            return this;
        }

        public Builder setSms() {
            mPermissionList.add(Manifest.permission.SEND_SMS);
            mPermissionList.add(Manifest.permission.RECEIVE_SMS);
            mPermissionList.add(Manifest.permission.READ_SMS);

            return this;
        }

        public Builder setMms() {
            mPermissionList.add(Manifest.permission.RECEIVE_MMS);

            return this;
        }

        public Builder setWapPush() {
            mPermissionList.add(Manifest.permission.RECEIVE_WAP_PUSH);

            return this;
        }

        public Builder setCamera() {
            mPermissionList.add(Manifest.permission.CAMERA);

            return this;
        }

        public PermissionType build() {
            return new PermissionType(this);
        }
    }

    public ArrayList<String> getPermissionList() {
        return mPermissionList;
    }
}