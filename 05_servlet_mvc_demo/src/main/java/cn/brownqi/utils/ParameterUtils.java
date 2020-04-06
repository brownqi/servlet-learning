package cn.brownqi.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {

    public static <T> T newModel(HttpServletRequest req, Class clazz) {
        String jsonString = "";

        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> parameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = req.getParameter(name);
            parameterMap.put(name, value);
        }

        if (parameterMap.isEmpty()) {
            String jsonTemp = "";
            try {
                ServletInputStream inputStream = req.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) !=/**/ null) {
                    sb.append(line);
                }
                String contentType = req.getContentType();
                if (contentType.contains("application/x-www-form-urlencoded")) {
                    Map<String, String> map = new HashMap<>();
                    String[] split = sb.toString().split("&");
                    for (int i = 0; i < split.length; i++) {
                        String[] kv = split[i].split("=");
                        map.put(kv[0], kv[1]);
                    }
                    jsonTemp = JSON.toJSONString(map);
                } else {
                    jsonTemp = JSON.toJSONString(sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonString = jsonTemp;
        }else {
            jsonString = JSON.toJSONString(parameterMap);
        }
        Object values = JSON.parseObject(jsonString, clazz);
        return (T) values;
    }

}
