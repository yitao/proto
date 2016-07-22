package com.backstage.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saysth.commons.http.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 地理位置工具
 *
 * @author KelvinZ
 */
public class LBSUtils {
    private static Logger log = LoggerFactory.getLogger(LBSUtils.class);

    public interface BAIDU_API {
        String GEOCODER_V1 = "http://api.map.baidu.com/geocoder";
        String GEOCODER_V2 = "http://api.map.baidu.com/geocoder/v2/";
    }

    /**
     * 公里单位
     */
    public static final double UNIT_KM = 1d;
    /**
     * 米单位
     */
    public static final double UNIT_M = 1000d;
    // radius一度近似等于111.12公里，使用半径条件时用半径(千米)乘以该数
    public static final double RADIUS_KM = 1 / 111.12;
    // 使用半径条件时用半径(米)乘以该数
    public static final double RADIUS_M = RADIUS_KM / UNIT_M;

    private static final double EARTH_RADIUS = 6378.137; // 地球半径，单位公里

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 校验纬度正确性
     *
     * @param lat
     * @return
     */
    public static boolean validLatitude(double lat) {
        if (lat > 90 || lat < -90) {
            return false;
        }
        return true;
    }

    /**
     * 校验经度正确性
     *
     * @param lng
     * @return
     */
    public static boolean validLongitude(double lng) {
        if (lng > 180 || lng < -180) {
            return false;
        }
        return true;
    }

    /**
     * 校验经纬度正确性
     *
     * @param coord
     * @return
     */
    public static boolean validCoord(double[] coord) {
        if (coord == null || coord.length != 2) {
            return false;
        }
        // 经度
        if (!validLongitude(coord[0])) {
            return false;
        }
        // 纬度
        if (!validLatitude(coord[1])) {
            return false;
        }
        return true;
    }

    /**
     * 校验经纬度正确性
     *
     * @param coord
     * @return
     */
    public static boolean validCoord(Double[] coord) {
        if (coord == null || coord.length != 2)
            return false;
        // 经度
        if (coord[0] == null || !validLongitude(coord[0])) {
            return false;
        }
        // 纬度
        if (coord[1] == null || !validLatitude(coord[1])) {
            return false;
        }
        return true;
    }

    /**
     * 计算两坐标间的公里数
     *
     * @param coord1
     * @param coord2
     * @return
     */
    public static double getDistance(Double[] coord1, Double[] coord2) {
        return getDistance(coord1[0], coord1[1], coord2[0], coord2[1]);
    }

    /**
     * 计算两坐标间的距离
     *
     * @param coord1
     * @param coord2
     * @param unit
     * @return
     */
    public static double getDistance(Double[] coord1, Double[] coord2, Double unit) {
        return getDistance(coord1[0], coord1[1], coord2[0], coord2[1], unit);
    }

    /**
     * 计算两坐标间的公里数
     *
     * @param coord1 [lng, lat]
     * @param coord2 [lng, lat]
     * @return
     */
    public static double getDistance(double[] coord1, double[] coord2) {
        Assert.isTrue(validCoord(coord1), "非法坐标");
        Assert.isTrue(validCoord(coord2), "非法坐标");
        return getDistance(coord1[0], coord1[1], coord2[0], coord2[1]);
    }

    /**
     * 计算两坐标间的公里数
     *
     * @param coord1 [lng, lat]
     * @param coord2 [lng, lat]
     * @return
     */
    public static double getDistance(double[] coord1, double[] coord2, Double unit) {
        Assert.isTrue(validCoord(coord1), "非法坐标");
        Assert.isTrue(validCoord(coord2), "非法坐标");
        return getDistance(coord1[0], coord1[1], coord2[0], coord2[1], unit);
    }

    /**
     * 计算两坐标间的公里数
     *
     * @param lon1 - 坐标1-经度
     * @param lat1 - 坐标1-纬度
     * @param lon2 - 坐标2-经度
     * @param lat2 - 坐标2-纬度
     * @return
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        return getDistance(lon1, lat1, lon2, lat2, UNIT_KM);
    }

    /**
     * 计算量坐标之间的距离，并转换为指定单位
     *
     * @param lon1 - 坐标1-经度
     * @param lat1 - 坐标1-纬度
     * @param lon2 - 坐标2-经度
     * @param lat2 - 坐标2-纬度
     * @param unit - 距离单位(UNIT_KM为公里, UNIT_M为米)
     * @return
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2, double unit) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS * unit;
        s = Math.round(s * 10000) / 10000d;
        return s;
    }

    /**
     * 根据坐标查找城市
     *
     * @param lat
     * @param lon
     * @return
     */
    public static String getCityName(String apiUrl, double lon, double lat) {
        String city = null;
        apiUrl = apiUrl.replace("{lat}", lat + "");
        apiUrl = apiUrl.replace("{lon}", lon + "");
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(apiUrl);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {
            log.error("getCityName failed!", ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        String result = sb.toString();
        int b = result.indexOf("\"city\":") + 8;
        int e = result.indexOf(",", b) - 1;
        city = result.substring(b, e);
        return city;
    }

    /**
     * 返回API结果
     *
     * @param apiUrl
     * @param params
     * @return
     */
    public static String getApiResult(String apiUrl, Map<String, String> params) {
        String referrer = "http://developer.baidu.com/map/ip-location-api.htm";
        String userAgent = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0";
        return getApiResult(apiUrl, params, referrer, userAgent);
    }

    /**
     * 返回API结果
     *
     * @param apiUrl
     * @param params
     * @return
     */
    public static String getApiResult(String apiUrl, Map<String, String> params, String referrer) {
        String userAgent = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0";
        return getApiResult(apiUrl, params, referrer, userAgent);
    }

    /**
     * 返回API结果
     *
     * @param apiUrl
     * @param params
     * @param referrer
     * @param userAgent
     * @return
     */
    public static String getApiResult(String apiUrl, Map<String, String> params, String referrer, String userAgent) {
        try {
            HttpHelper httpHelper = HttpHelper.connect(apiUrl).data(params);
            if (StringUtils.isNotBlank(referrer)) {
                httpHelper.referrer(referrer);
            }
            if (StringUtils.isNotBlank(userAgent)) {
                httpHelper.userAgent(userAgent);
            }
            return httpHelper.get().html();
        } catch (Exception e) {
            log.error("Error", e);
        }
        return null;
    }

    /**
     * 截成标准长度的坐标
     *
     * @param latOrLon
     * @return
     */
    public static double chopLatLon(double latOrLon) {
        return Math.round(latOrLon * 1000000) / 1000000D;
    }

    /**
     * 百度提供的计算两点距离的方法： http://developer.baidu.com/map/sdkandev-question.htm
     */
    static double DEF_PI = Math.PI; // PI
    static double DEF_2PI = 2 * DEF_PI; // 2*PI
    static double DEF_PI180 = DEF_PI / 180.0; // PI/180.0
    static double DEF_R = 6370693.5; // radius of earth

    public static double getShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public static double getLongDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }

    /**
     * 将GPS坐标转换为百度坐标
     *
     * @param lon
     * @param lat
     * @return
     */
    public static double[] turnGps2BaiduXY(double lon, double lat) {
        try {
            String param = "ak=C1ff4659765b7ad281c6fe11169a886f&from=1&to=5&coords=" + lon + "," + lat;
            String resp = HttpHelper.connect("http://api.map.baidu.com/geoconv/v1/").timeout(2000).post(param).html();
            log.info(resp);
            JSONObject jsonObj = JSON.parseObject(resp);
            Integer status = (Integer) jsonObj.get("status");
            if (status != null && status == 0) {
                JSONArray jsonArr = jsonObj.getJSONArray("result");
                JSONObject pos = (JSONObject) jsonArr.get(0);
                return new double[]{pos.getDouble("x"), pos.getDouble("y")};
            } else {
                return new double[]{lon, lat};
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new double[]{lon, lat};
        }
    }

    public static String getIpLocation(String ip) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("ak", "C1ff4659765b7ad281c6fe11169a886f");
            params.put("ip", ip);
            String resp = HttpHelper.connect("http://api.map.baidu.com/location/ip").timeout(3000).data(params).get()
                    .html();
            log.info(resp);
            JSONObject jsonObj = JSON.parseObject(resp);
            String address = jsonObj.getString("address");
            return address;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得坐标
     *
     * @param lng
     * @param lat
     * @return
     */
    public static Double[] getCoord(String lng, String lat) {
        try {
            Double[] coord = new Double[]{Double.parseDouble(lng), Double.parseDouble(lat)};
            return coord;
        } catch (Exception ex) {
            log.error("坐标错误");
        }
        return null;
    }

}
