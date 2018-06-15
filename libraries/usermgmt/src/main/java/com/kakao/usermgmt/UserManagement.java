/*
  Copyright 2014-2017 Kakao Corp.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.kakao.usermgmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService.AgeLimit;
import com.kakao.auth.ISession;
import com.kakao.auth.Session;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.network.tasks.ITaskQueue;
import com.kakao.network.tasks.KakaoResultTask;
import com.kakao.network.tasks.KakaoTaskQueue;
import com.kakao.usermgmt.api.UserApi;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.AgeAuthResponse;
import com.kakao.usermgmt.response.model.UserProfile;

/**
 * UserManagement API 요청을 담당한다.
 * @author MJ
 */
public class UserManagement {

    /**
     * 추가 동의가 필요로 하는 인증정보를 response에 포함하고 싶은 경우 해당 키 리스트.
     * 현재는 "account_ci"만 제공("account_ci" 추가 동의 필요하므로 해당 동의를 받지 않은 사용자에게는 추가 동의창이 뜨게 됨)
     */
    public enum AgeAuthProperty {
        ACCOUNT_CI("account_ci");

        final private String value;
        AgeAuthProperty(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

//    private final AuthNetworkService networkService;
    private UserApi api;
    private ITaskQueue taskQueue;
    private ISession session;

    UserManagement(UserApi api, ITaskQueue taskQueue, ISession session) {
        this.api = api;
        this.taskQueue = taskQueue;
        this.session = session;
    }

    private static UserManagement instance = new UserManagement(UserApi.getInstance(),
            KakaoTaskQueue.getInstance(), Session.getCurrentSession());

    public static UserManagement getInstance() {
        return instance;
    }

    /**
     * 가입 요청
     * @param callback signup 요청 결과에 대한 callback
     * @param properties 가입시 받은 사용자 정보
     */
    public void requestSignup(final ApiResponseCallback<Long> callback, final Map<String, String> properties) {
        taskQueue.addTask(new KakaoResultTask<Long>(callback) {
            @Override
            public Long call() throws Exception {
                return api.requestSignup(properties);
            }
        });
    }

    /**
     * 로그아웃 요청
     * @param callback logout 요청 결과에 대한 callback
     */
    public void requestLogout(final LogoutResponseCallback callback) {
        taskQueue.addTask(new KakaoResultTask<Long>(callback) {
            @Override
            public Long call() throws Exception {
                return api.requestLogout();
            }

            @Override
            public void onDidEnd() {
                super.onDidEnd();
                session.close();
            }
        });
    }

    /**
     * Unlink 요청
     * @param callback unlink 요청 결과에 대한 handler
     */
    public void requestUnlink(final UnLinkResponseCallback callback) {
        taskQueue.addTask(new KakaoResultTask<Long>(callback) {
            @Override
            public Long call() throws Exception {
                return api.requestUnlink();
            }

            @Override
            public void onDidEnd() {
                super.onDidEnd();
                session.close();
            }
        });
    }

    /**
     * @param callback updateProfile 요청 결과에 대한 callback
     * @param nickName 사용자 이름
     * @param thumbnailImagePath 사용자 profile image thumbnail image path
     * @param profileImage 사용자의 profile image path
     * @param properties 저장할 사용자 extra 정보
     */
    public void requestUpdateProfile(final ApiResponseCallback<Long> callback, final String nickName, final String thumbnailImagePath, final String profileImage,  final Map<String, String> properties) {
        Map<String, String> userProperties = properties;
        if (userProperties == null) {
            userProperties = new HashMap<>();
        }
        properties.put(StringSet.nickname, nickName);
        properties.put(StringSet.thumbnail_image, thumbnailImagePath);
        properties.put(StringSet.profile_image, profileImage);

        requestUpdateProfile(callback, userProperties);
    }

    /**
     * 사용자정보 저장 요청
     * @param callback updateProfile 요청 결과에 대한 callback
     * @param properties 저장할 사용자 정보
     */
    public void requestUpdateProfile(final ApiResponseCallback<Long> callback, final Map<String, String> properties) {
        taskQueue.addTask(new KakaoResultTask<Long>(callback) {
            @Override
            public Long call() throws Exception {
                return api.requestUpdateProfile(properties);
            }
        });
    }


    /**
     * 사용자정보 요청
     * @param callback me 요청 결과에 대한 callback
     */
    public void requestMe(final MeResponseCallback callback) {
        requestMe(callback, null, false);
    }

    /**
     * 사용자 정보 일부나 이미지 리소스를 https로 받고 싶은 경우의 사용자정보 요청
     * @param callback me 요청 결과에 대한 callback
     * @param propertyKeys 사용자 정보의 키 리스트
     * @param secureResource 이미지 url을 https로 반환할지 여부
     */
    public void requestMe(final MeResponseCallback callback, final List<String> propertyKeys, final boolean secureResource) {
        taskQueue.addTask(new KakaoResultTask<UserProfile>(callback) {
            @Override
            public UserProfile call() throws Exception {
                return api.requestMe(propertyKeys, secureResource);
            }
        });
    }

    /**
     * 토큰으로 인증날짜와 CI값을 얻는다.
     * (제휴를 통해 권한이 부여된 특정 앱에서만 호출이 가능합니다.)
     * @param callback 요청 결과에 대한 callback
     * @param ageLimit {@link AgeLimit} enum representing minimum age to be allowed
     * @param propertyKeyList List of {@link AgeAuthProperty} enum
     */
    public void requestAgeAuthInfo(final ResponseCallback<AgeAuthResponse> callback, final AgeLimit ageLimit, final List<AgeAuthProperty> propertyKeyList) {
        List<String> propertyKeys = null;
        if (propertyKeyList != null && propertyKeyList.size() > 0) {
            propertyKeys = new ArrayList<>();
            for (AgeAuthProperty property : propertyKeyList) {
                propertyKeys.add(property.getValue());
            }
        }
        final List<String> finalPropertyKeys = propertyKeys;
        taskQueue.addTask(new KakaoResultTask<AgeAuthResponse>(callback) {
            @Override
            public AgeAuthResponse call() throws Exception {
                return api.requestAgeAuthInfo(ageLimit, finalPropertyKeys);
            }
        });
    }
}
