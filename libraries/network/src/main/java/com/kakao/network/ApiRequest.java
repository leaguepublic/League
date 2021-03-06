package com.kakao.network;

import android.net.Uri;
import android.os.Build;

import com.kakao.network.multipart.Part;
import com.kakao.util.IConfiguration;
import com.kakao.util.helper.CommonProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 로그인이 필요하지 않을 때 사용하는 Request 클래스. 앱키 정보만 보낸다.
 * Created by kevin.kang on 2016. 11. 25..
 */

public abstract class ApiRequest implements IRequest {
    protected static final String POST = "POST";
    protected static final String GET = "GET";
    protected static final String DELETE = "DELETE";

    private String kaHeader;
    private String extras;
    private String appKey;
    private String appVer;

    protected ApiRequest() {
    }

    protected ApiRequest(final IConfiguration configuration) {
        this.appKey = configuration.getAppKey();
        this.kaHeader = configuration.getKAHeader();
        this.extras = configuration.getExtras();
        this.appVer = configuration.getAppVer();
    }

    @Override
    public abstract String getMethod();

    @Override
    public String getUrl() {
        if (getUriBuilder() != null) {
            return getUriBuilder().build().toString();
        }
        return "";
    }

    public Uri.Builder getUriBuilder() {
        return new Uri.Builder().scheme(ServerProtocol.SCHEME);
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put(CommonProtocol.KA_HEADER_KEY, kaHeader);
        if (!header.containsKey("Content-Type")) {
            header.put("Content-Type", "application/x-www-form-urlencoded");
        }

        if (!header.containsKey("Accept")) {
            header.put("Accept", "*/*");
        }

        if (!header.containsKey("User-Agent")) {
            header.put("User-Agent", getHttpUserAgentString());
        }

        header.put(ServerProtocol.AUTHORIZATION_HEADER_KEY, ServerProtocol.KAKAO_AK_HEADER_KEY + ServerProtocol.AUTHORIZATION_HEADER_DELIMITER + getAppKey());

        return header;
    }

    @Override
    public List<Part> getMultiPartList() {
        return new ArrayList<Part>();
    }

    @Override
    public String getBodyEncoding() {
        return "UTF-8";
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppVer() {
        return appVer;
    }

    public String getExtras() {
        return extras;
    }

    public String getHttpUserAgentString() {
        return "os/" + CommonProtocol.OS_ANDROID + "-" + Build.VERSION.SDK_INT + " ";
    }

    public void setConfiguration(final IConfiguration configuration) {
        setAppKey(configuration.getAppKey());
        setKaHeader(configuration.getKAHeader());
        setAppVer(configuration.getAppVer());
        setExtras(configuration.getExtras());
    }

    public void setKaHeader(String kaHeader) {
        this.kaHeader = kaHeader;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }
}
