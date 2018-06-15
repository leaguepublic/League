package com.kakao.auth.network;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.StringSet;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.auth.authorization.accesstoken.TestAccessToken;
import com.kakao.auth.mocks.TestAppConfig;
import com.kakao.auth.mocks.TestAuthorizedRequest;
import com.kakao.auth.mocks.TestNetworkService;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.auth.network.response.AuthorizedApiResponse;
import com.kakao.auth.network.response.InsufficientScopeException;
import com.kakao.network.NetworkService;
import com.kakao.network.response.ApiResponseStatusError;
import com.kakao.network.response.ResponseBody;
import com.kakao.network.response.ResponseStringConverter;
import com.kakao.network.tasks.ITaskQueue;
import com.kakao.network.tasks.KakaoResultTask;
import com.kakao.test.common.KakaoTestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Tests Authorized request (which means API request with access token).
 *
 * @author kevin.kang. Created on 2017. 12. 4..
 */

public class DefaultAuthNetworkServiceTest extends KakaoTestCase {
    private DefaultAuthNetworkService authNetworkService;


    private AccessToken tokenInfo;
    private NetworkService networkService;
    private ApiErrorHandlingService errorHandlingService;


    public void setup() {
        super.setup();
        networkService = Mockito.spy(new TestNetworkService());
        ITaskQueue taskQueue = Mockito.spy(new ITaskQueue() {
            @Override
            public <T> Future<T> addTask(KakaoResultTask<T> task) {
                return null;
            }
        });
        tokenInfo = Mockito.spy(new TestAccessToken());
        errorHandlingService = Mockito.spy(new TestApiErrorHandlingService());

        authNetworkService = Mockito.spy(new DefaultAuthNetworkService(networkService, taskQueue));
        authNetworkService.setTokenInfo(tokenInfo);
        authNetworkService.setErrorHandlingService(errorHandlingService);
        authNetworkService.setConfiguration(TestAppConfig.createTestAppConfig());
    }

    /**
     * Tests normal case of API request with access token.
     */
    @Test
    public void request() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        try {
            Mockito.doReturn(converter.convert(getJsonString())).when(networkService).request(request, converter);
            AccessTokenInfoResponse response =
                    authNetworkService.request(request, converter);
            assertEquals(1234, response.getUserId());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    /**
     * Tests when access token has expired but can be refreshed with refresh token.
     */
    @Test
    public void requestWithExpiredToken() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        try {
            Mockito.doReturn(false).doReturn(true).when(tokenInfo).hasValidAccessToken();
            Mockito.doReturn(true).when(errorHandlingService).shouldRetryAfterTryingRefreshToken();
            Mockito.doReturn(converter.convert(getJsonString())).when(networkService).request(request, converter);
            AccessTokenInfoResponse response = authNetworkService.request(request, converter);
            assertEquals(1234, response.getUserId());
        } catch (AuthorizedApiResponse.SessionClosedException e) {
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    /**
     * Tests when both access token and refresh token have expired.
     */
    @Test
    public void requestWithExpiredTokenAndRefreshToken() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        try {
            Mockito.doReturn(false).when(tokenInfo).hasValidAccessToken();
            Mockito.doReturn(false).when(errorHandlingService).shouldRetryAfterTryingRefreshToken();
            authNetworkService.requestList(request, converter);
            fail();
        } catch (AuthorizedApiResponse.SessionClosedException e) {
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    /**
     * Tests authorized request with {@link ApiErrorCode#INVALID_TOKEN_CODE} and {@link ApiErrorHandlingService#shouldRetryWithApiError(ApiResponseStatusError)}
     * returning false (meaning there is no retry).
     *
     * This should produce Exception
     */
    @Test
    public void requestWithInvalidToken() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        try {
            Mockito.doThrow(getInvalidTokenError())
                    .when(networkService).request(request, converter);
            authNetworkService.request(request, converter);
            fail();
        } catch (ApiResponseStatusError e) {
            assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED, e.getHttpStatusCode());
            assertEquals(ApiErrorCode.INVALID_TOKEN_CODE, e.getErrorCode());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests authorized request with {@link ApiErrorCode#INVALID_TOKEN_CODE} and {@link ApiErrorHandlingService#shouldRetryWithApiError(ApiResponseStatusError)}
     * returning false.
     *
     * This should produce {@link InsufficientScopeException}
     */
    @Test
    public void requestWithInvalidScope() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        ApiResponseStatusError error = getInvalidScopeError();
        try {
            Mockito.doThrow(error)
                    .when(networkService).request(request, converter);
            authNetworkService.request(request, converter);
            fail();
        } catch (ApiResponseStatusError e) {
            assertEquals(HttpURLConnection.HTTP_FORBIDDEN, e.getHttpStatusCode());
            assertEquals(ApiErrorCode.INVALID_SCOPE_CODE, e.getErrorCode());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests authorized request with {@link ApiErrorCode#INVALID_TOKEN_CODE} and {@link ApiErrorHandlingService#shouldRetryWithApiError(ApiResponseStatusError)}
     * returning true.
     *
     * This should result in a successful response.
     */
    @Test
    public void requestWithApiErrorAndRetry() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<AccessTokenInfoResponse> converter = AccessTokenInfoResponse.CONVERTER;
        ApiResponseStatusError error = getInvalidTokenError();
        try {
            Mockito.doReturn(true).when(errorHandlingService).shouldRetryWithApiError(error);
            Mockito.doThrow(error)
                    .doReturn(converter.convert(getJsonString()))
                    .when(networkService).request(request, converter);
            AccessTokenInfoResponse response =
                    authNetworkService.request(request, converter);
            assertEquals(1234, response.getUserId());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void requestList() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<String> converter = getStringConverter();
        List<String> response = new ArrayList<>();
        try {
            Mockito.doReturn(response).when(networkService).requestList(request, converter);
            List<String> result = authNetworkService.requestList(request, converter);
            assertEquals(response, result);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void requestListWithInvalidToken() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<String> converter = getStringConverter();
        ApiResponseStatusError error = getInvalidTokenError();
        try {
            Mockito.doThrow(error).when(networkService).requestList(request, converter);
            authNetworkService.requestList(request, converter);
            fail();
        } catch (ApiResponseStatusError e) {
            assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED, e.getHttpStatusCode());
            assertEquals(ApiErrorCode.INVALID_TOKEN_CODE, e.getErrorCode());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void requestListWithInvalidScope() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<String> converter = getStringConverter();
        ApiResponseStatusError error = getInvalidScopeError();
        try {
            Mockito.doThrow(error).when(networkService).requestList(request, converter);
            authNetworkService.requestList(request, converter);
            fail();
        } catch (ApiResponseStatusError e) {
            assertEquals(HttpURLConnection.HTTP_FORBIDDEN, e.getHttpStatusCode());
            assertEquals(ApiErrorCode.INVALID_SCOPE_CODE, e.getErrorCode());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void requestListWithInvalidScopeAndRetry() {
        AuthorizedRequest request = new TestAuthorizedRequest();
        ResponseStringConverter<String> converter = getStringConverter();
        ApiResponseStatusError error = getInvalidScopeError();
        List<String> response = new ArrayList<>();
        try {
            Mockito.doReturn(true).when(errorHandlingService).shouldRetryWithApiError(error);
            Mockito.doThrow(error)
                    .doReturn(response)
                    .when(networkService).requestList(request, converter);
            List<String> result = authNetworkService.requestList(request, converter);
            assertEquals(response, result);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    ResponseStringConverter<String> getStringConverter() {
        return Mockito.spy(new ResponseStringConverter<String>() {
            @Override
            public String convert(String data) {
                return data;
            }
        });
    }

    ResponseBody getResponseBody() {
        ResponseBody body = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(StringSet.id, 1234);
            jsonObject.put(StringSet.expiresInMillis, System.currentTimeMillis() + 1000 * 60 * 60 * 12);
            body = new ResponseBody(HttpURLConnection.HTTP_OK, jsonObject);
        } catch (JSONException|ResponseBody.ResponseBodyException e) {
            fail(e.toString());
        }
        return body;
    }

    String getJsonString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(StringSet.id, 1234);
            jsonObject.put(StringSet.expiresInMillis, System.currentTimeMillis() + 1000 * 60 * 60 * 12);
        } catch (JSONException e) {
            fail(e.toString());
        }
        return jsonObject.toString();
    }

    ApiResponseStatusError getInvalidTokenError() {
        return new ApiResponseStatusError(ApiErrorCode.INVALID_TOKEN_CODE, "error_message", HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    ApiResponseStatusError getInvalidScopeError() {
        return new ApiResponseStatusError(ApiErrorCode.INVALID_SCOPE_CODE, "error_message", HttpURLConnection.HTTP_FORBIDDEN);
    }
}
