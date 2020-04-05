package cn.brownqi.controller.good;

import cn.brownqi.model.Good;
import cn.brownqi.service.GoodService;
import cn.brownqi.service.impl.GoodServiceImpl;
import cn.brownqi.utils.ParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goodServlet/detail")
public class GoodDetail extends HttpServlet {
    GoodService goodService = new GoodServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Good good = ParameterUtils.newModel(req, Good.class);
            Good goodDetail = goodService.searchGoodDetail(good.getGoodId());
            req.setAttribute("good",goodDetail);
            req.getRequestDispatcher("/goodDetails.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
