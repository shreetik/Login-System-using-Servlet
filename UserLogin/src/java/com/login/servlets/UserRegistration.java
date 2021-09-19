
package com.login.servlets;

import com.login.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String uid=request.getParameter("txtuserid");
		String upass=request.getParameter("txtpass");
		String uname=request.getParameter("txtname");
		String uadd=request.getParameter("txtadd");
		String uphone=request.getParameter("txtnum");
		String uemail=request.getParameter("txtemail");
		String ugender=request.getParameter("txtgender");
                String udob=request.getParameter("txtdob");
                String upic=request.getParameter("txtpic");
		
		try {
                    Connection conn = ConnectionProvider.getConnection();
                  
                        String sql = "insert into usertable values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setString(2,upass);
			ps.setString(3,uname);
			ps.setString(4,uadd);
			ps.setString(5,uphone);
			ps.setString(6,uemail);
			ps.setString(7,ugender);
                        ps.setString(8,udob);
                        ps.setString(9,upic);
			int dd=ps.executeUpdate();
			if(dd>0)
			{
				pw.println("<h1>Registration Successfully</h1>");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>Registration Faild!!</h2>"+e);
		}
}
		
	}
