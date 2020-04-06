package cn.brownqi.controller.good;

import cn.brownqi.model.Good;
import cn.brownqi.rest.Result;
import cn.brownqi.service.GoodService;
import cn.brownqi.service.impl.GoodServiceImpl;
import cn.brownqi.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/goodServlet/search")
public class GoodSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService = new GoodServiceImpl();
        req.setCharacterEncoding("UTF-8");
        Result result = null;
        try{
            String goodname = req.getParameter("goodname");
            List<Good> goods = goodService.selectGoodsByName(goodname);
            result = Result.OK(2000,"成功",goods);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONUtil.writeJSON(resp,result);
        }
    }
}
