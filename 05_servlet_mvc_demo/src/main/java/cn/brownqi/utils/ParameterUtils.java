package cn.brownqi.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ParameterUtils {

    public static <T> T newModel(HttpServletRequest request,Class clazz){
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            parameterMap.put(name,value);
        }

        String jsonString = JSON.toJSONString(parameterMap);

        Object values = JSON.parseObject(jsonString,clazz);
        return (T) values;
    }

}
