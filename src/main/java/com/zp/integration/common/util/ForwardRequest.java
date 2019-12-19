package com.zp.integration.common.util;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * springBoot 实现项目（没有使用springCloud 进行开发）
 * 有时候需要调用其他的 java 后台 api 进行数据获取，发现了很多问题，最后调试完毕，
 * 记录下，访问方式包含 get 与 post，post 访问时需要确定接收的控制层是以实体类型接收还是以字段类型接收，
 * 然后再 params 这个 map 中放入键值对数据，
 * map.put("paramType", "entity");
 * 放入这个键值对表示对方使用实体类型接收，如果不放入这个键值对，就是使用字段类型接收数据。
 * <p>
 * post 发送数据需要两种形式：
 * 如果接收的 controller 层使用实体接收，就需要进行以 json 格式发送，如果以单独字段进行接收，就需要进行键值对的形式发送。
 * <p>
 * 参照：java 后台 api 间的相互调用【https://blog.csdn.net/xiaobenteng/article/details/78538993】
 */

public class ForwardRequest {

    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 60000;
    private static final int DEF_READ_TIMEOUT = 60000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /**
     * 调用 api 示例
     *
     * @param url    访问地址路径 url
     * @param params 提交的参数 map 集合
     * @param method 访问方式 GET/POST
     * @param token  权限验证，没有则为空
     * @return
     */
    public static String apiInvocation(String url, Map params, String method, String token) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String res = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                url = url + "?" + urlEncode(params);
            }
            URL strUrl = new URL(url);
            connection = (HttpURLConnection) strUrl.openConnection();
            if (method == null || method.equals("GET")) {
                connection.setRequestMethod("GET");
            } else {
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                if ("entity".equals(params.get("paramType"))) {
                    connection.setRequestProperty("Content-Type", "application/json");
                } else {
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                }
                connection.setRequestProperty("Content-Length", "contnet.length");
            }
            connection.setRequestProperty("User-agent", userAgent);
            connection.setRequestProperty("Authorization", token);
            connection.setUseCaches(false);
            connection.setConnectTimeout(DEF_CONN_TIMEOUT);
            connection.setReadTimeout(DEF_READ_TIMEOUT);
            connection.setInstanceFollowRedirects(false);
            connection.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    if ("entry".equals(params.get("paramType"))) {
                        // 如果接收参数为实体类型，就进行 json 转码后发送
                        //out.writeBytes(GsonTool.toJson(params))
                        out.writeBytes(new Gson().toJson(params));
                    } else {
                        //如果接收的参数为几个字段类型，就是用键值对的形式进行 urlEncode 进行编码发送
                        out.writeBytes(urlEncode(params));
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            res = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return res;
    }

    // 将 map 型转为请求参数型（get 访问方式使用）
    public static String urlEncode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : data.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue() + "UTF-8"))
                    .append("&");
        }
        return sb.toString();
    }

    public static String getPhoto64(String photoObjectInfo, String idCard) {
        JSONObject object/* = new JSONObject()*/;
        try {
            object = JSONObject.fromObject(photoObjectInfo);
            if (object.getInt("code") == 0) {
                if (object.getJSONObject("obj").getJSONObject(idCard).size() != 0) {
                    String res = object.getJSONObject("obj").getJSONObject(idCard).getString("url");
                    return res;
                } else
                    return null;
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static void main(String[] ags) throws Exception {
        Map map = new HashMap();
        map.put("key", "xxx");
        map.put("id", "1");

        System.out.println(apiInvocation("http://localhost:8080/relation-web/visual/getFamilyRelationList", map, "POST", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTAwMDAwMDAwMDE0OSIsImNyZWF0ZWQiOjE1MTAxOTUxMDk0ODIsImV4cCI6MTUxMDc5OTkwOX0.A7k7FVpbFc56RA6y_l3m3hDDmMseL6nct2jW79Q9YIrsG1CeiRV3opAw7iRVPx9JziALA4Rg9PTByN-NbU-Zeg"));
    }
}
