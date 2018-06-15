package com.kakao.auth.mocks;

import com.kakao.util.IConfiguration;
import com.kakao.util.RequestConfiguration;

/**
 * @author kevin.kang. Created on 2017. 6. 1..
 */

public class TestAppConfig extends RequestConfiguration {
    public TestAppConfig(String appKey, String clientSecret, String keyHash, String kaHeader, String appVer, String packageName, String extras) {
        super(appKey, clientSecret, keyHash, kaHeader, appVer, packageName, extras);
    }

    public static IConfiguration createTestAppConfig() {
        return new RequestConfiguration("sample_app_key", "client_secret",
                "sample_key_hash", "sample_ka_header", "sample_app_ver",
                "sample_package_name", "extras");
    }
}
