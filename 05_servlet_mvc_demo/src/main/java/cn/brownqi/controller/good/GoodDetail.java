package cn.brownqi.controller.good;

import cn.brownqi.model.Good;
import cn.brownqi.rest.Result;
import cn.brownqi.service.GoodService;
import cn.brownqi.service.impl.GoodServiceImpl;
import cn.brownqi.utils.JSONUtil;
import cn.brownqi.utils.ParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goodServlet/detail/*")
public class GoodDetail extends HttpServlet {
    GoodService goodService = new GoodServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        try{
            Good good = ParameterUtils.newModel(req, Good.class);
            Good goodDetail = goodService.searchGoodDetail(good.getGoodId());
            result = Result.OK(2000,"成功",goodDetail);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONUtil.writeJSON(resp,result);
        }
    }
}
