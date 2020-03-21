package cn.brownqi.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 16:31
 */
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("servlet1: "+username);

        request.setAttribute("key1","servlet1 mark");

        //转发到servlet2
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");

        //到servlet2
        requestDispatcher.forward(request,response);
    }
}
