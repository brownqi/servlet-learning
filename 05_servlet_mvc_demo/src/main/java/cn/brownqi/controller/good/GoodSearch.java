package cn.brownqi.controller.good;

import cn.brownqi.model.Good;
import cn.brownqi.service.GoodService;
import cn.brownqi.service.impl.GoodServiceImpl;

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodService goodService = new GoodServiceImpl();
        req.setCharacterEncoding("UTF-8");
        try{
            String goodname = req.getParameter("goodname");
            List<Good> goods = goodService.selectGoodsByName(goodname);
            req.setAttribute("goods",goods);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
