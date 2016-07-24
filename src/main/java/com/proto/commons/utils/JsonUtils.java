package com.proto.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * Created by and on 2016/7/24.
 */
public class JsonUtils {
    public static final int TYPE_FASTJSON = 0;
    public static final int TYPE_GSON = 1;

    public JsonUtils() {
    }

    public static final String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static final String toJson(Object obj, SerializerFeature... features) {
        return JSON.toJSONString(obj, features);
    }

    public static final String toJson(Object obj, boolean format) {
        return JSON.toJSONString(obj, format);
    }

    public static final String toJson(Object obj, final String[] fields, final boolean ignore, SerializerFeature... features) {
        if(fields != null && fields.length >= 1) {
            if(features == null) {
                features = new SerializerFeature[]{SerializerFeature.QuoteFieldNames};
            }

            return JSON.toJSONString(obj, new PropertyFilter() {
                public boolean apply(Object object, String name, Object value) {
                    for(int i = 0; i < fields.length; ++i) {
                        if(name.equals(fields[i])) {
                            return !ignore;
                        }
                    }

                    return ignore;
                }
            }, features);
        } else {
            return toJson(obj);
        }
    }

    public static final <E> E parse(String json, String path) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int i = 0; i < keys.length - 1; ++i) {
            obj = obj.getJSONObject(keys[i]);
        }

        return (E) obj.get(keys[keys.length - 1]);
    }

    public static final <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static final <T> T parse(String json, String path, Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int inner = 0; inner < keys.length - 1; ++inner) {
            obj = obj.getJSONObject(keys[inner]);
        }

        String var6 = obj.getString(keys[keys.length - 1]);
        return parse(var6, clazz);
    }

    public static final <T> List<T> parseArray(Object obj, String[] fields, boolean ignore, Class<T> clazz, SerializerFeature... features) {
        String json = toJson(obj, fields, ignore, features);
        return parseArray(json, clazz);
    }

    public static final <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static final <T> List<T> parseArray(String json, String path, Class<T> clazz) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int inner = 0; inner < keys.length - 1; ++inner) {
            obj = obj.getJSONObject(keys[inner]);
        }

        String var7 = obj.getString(keys[keys.length - 1]);
        List ret = parseArray(var7, clazz);
        return ret;
    }

    public static final String correctJson(String invalidJson) {
        String content = invalidJson.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
        return content;
    }

    public static final String formatJson(String json) {
        Map map = (Map)JSON.parse(json);
        return JSON.toJSONString(map, true);
    }

    public static final String getSubJson(String json, String path) {
        String[] keys = path.split(",");
        JSONObject obj = JSON.parseObject(json);

        for(int i = 0; i < keys.length - 1; ++i) {
            obj = obj.getJSONObject(keys[i]);
        }

        return obj != null?obj.getString(keys[keys.length - 1]):null;
    }
}