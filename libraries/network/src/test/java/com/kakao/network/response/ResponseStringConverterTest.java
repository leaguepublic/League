package com.kakao.network.response;

import com.kakao.test.common.KakaoTestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author kevin.kang. Created on 2017. 12. 6..
 */

public class ResponseStringConverterTest extends KakaoTestCase {
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void convertList() {
        ResponseStringConverter<String> converter = new ResponseStringConverter<String>() {
            @Override
            public String convert(String data) {
                return data;
            }
        };
        JSONArray array = new JSONArray();
        array.put("string1");
        array.put("string2");
        array.put("string3");

        List<String> converted = converter.convertList(array.toString());

        try {
            Assert.assertEquals(array.getString(0), converted.get(0));
            Assert.assertEquals(array.getString(1), converted.get(1));
            Assert.assertEquals(array.getString(2), converted.get(2));
        } catch (JSONException e) {
            Assert.fail(e.toString());
        }
    }
}
