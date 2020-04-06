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

@WebServlet("/goodServlet/index")
public class GoodIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService = new GoodServiceImpl();
        Result result = null;
        try{
            List<Good> goods = goodService.selectAllGoods();
            result = Result.OK(2000,"成功",goods);
            req.setAttribute("goods",goods);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONUtil.writeJSON(resp,result);
        }
    }
}
