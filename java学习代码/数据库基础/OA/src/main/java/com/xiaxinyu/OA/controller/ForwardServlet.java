package com.xiaxinyu.OA.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 页面跳转Servlet
 */
@WebServlet(name="ForwardServlet",urlPatterns = "/forward/*")
public class ForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        /**
         * /forward/form
         * /forward/a/b/c/form
         */
        String subUrl = uri.substring(1);
        String page = subUrl.substring(subUrl.indexOf('/'));
        req.getRequestDispatcher(page + ".ftl").forward(req,resp);
    }
}
