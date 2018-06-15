package com.kakao.util.helper;

import com.kakao.test.common.KakaoTestCase;
import com.kakao.util.helper.log.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kevin.kang. Created on 2017. 12. 6..
 */

public class JsonTest extends KakaoTestCase {
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void testJSONObject() {
        JSONArray array = new JSONArray();
        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();

        try {
            object1.put("key1", "value1");
            object2.put("key2", "value2");
            object3.put("key3", "value3");

            array.put(object1);
            array.put(object2);
            array.put(object3);

            String string = array.getString(0);
            Logger.e(string);
        } catch (JSONException e) {
            Assert.fail();
        }
    }
}
