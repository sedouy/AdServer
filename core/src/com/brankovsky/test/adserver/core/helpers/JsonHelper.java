package com.brankovsky.test.adserver.core.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Алексей on 20.06.2017.
 */
public final class JsonHelper {
    private JsonHelper() {
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> klass) {
        try
        {
            return mapper.readValue(jsonString, klass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
