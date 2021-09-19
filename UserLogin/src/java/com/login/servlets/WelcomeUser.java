
package com.login.servlets;

import com.login.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Statement;

@WebServlet("/WelcomeUser")
public class WelcomeUser extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
                        Connection conn = ConnectionProvider.getConnection();
			PreparedStatement ps=conn.prepareStatement("select Password,UserName from usertable where Email=?");
			String s1=request.getParameter("txtuser");
			String s2=request.getParameter("txtpass");
			
			ps.setString(1,s1);
			//ps.setString(2,s2);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String pass=rs.getString(1);
				String name=rs.getString(2);
			    if(pass.equals(s2))
			    {
						
				pw.println("<html><body bgcolor='cyan'><font color='red'><center>");
				pw.println("<b>welcome "+name+" to website</b><br>");
			
		
					pw.println("<a href='changePassword.html'>changePassword</a><br> <br>");
					pw.println("<a href='editProfile.html'>EditProfile</a><br> <br>");
					pw.println("<a href='index.html'>LogOut</a> <br> <br> </body>");
				
				}else {
					
				pw.println("<body bgcolor='cyan'><h1>Invalid userId and password </h1></body>");
                                
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
                        pw.println("<h1>Registration Faild!!</h2>"+e);
		}
		
	
	}}
