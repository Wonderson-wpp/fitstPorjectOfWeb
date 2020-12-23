package com.peng.bookstore.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String methodName = request.getParameter("method");
        if (methodName == null || methodName.trim().isEmpty()) {
            throw new RuntimeException("您没有设置方法名");
        }
        String result = null;
        Class<? extends BaseServlet> thisClass = this.getClass();
        try {
            Method method = thisClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            result = (String) method.invoke(this, request, response);
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行您传递的方法" + methodName + "失败");
        }
        try {
            if (result == null || result.trim().isEmpty()) {
                return;
            } else if (!result.contains(":")) {
                request.getRequestDispatcher(result).forward(request, response);
            }else {
                int index = result.indexOf(":");
                String task = result.substring(0, index);
                String path = result.substring(index + 1);
                if ("forward".equals(task) || "FORWARD".equals(task) || "f".equals(task) || "F".equals(task)) {
                    request.getRequestDispatcher(path).forward(request, response);
                } else if ("redirect".equalsIgnoreCase(task) || "f".equalsIgnoreCase(task)) {
                    response.sendRedirect(request.getContextPath() + path);
                } else {
                    throw new RuntimeException("您想要执行的操作 " + task + ", 当前系统未提供");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行您传递的结尾行为" + result + "失败");
        }
    }
}
