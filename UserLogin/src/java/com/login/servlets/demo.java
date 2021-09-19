
package com.login.servlets;

import com.login.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet(name = "demo", urlPatterns = {"/demo"})
public class demo extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            Connection conn = ConnectionProvider.getConnection();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet demo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet demo at " + conn + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }



}
