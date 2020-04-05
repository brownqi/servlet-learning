package cn.brownqi.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONUtil {

    public static  void writeJSON(HttpServletResponse resp, Object data){
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
             writer = resp.getWriter();
             writer.write(JSON.toJSONString(data));
             writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

}
