
package com.login.servlets;

import com.login.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPassword=request.getParameter("cpass");
		String Newpass=request.getParameter("newpass");
		String conpass=request.getParameter("conpass");
		String pass="";
		String email="";
		PrintWriter out=response.getWriter();
		try{

                 Connection conn = ConnectionProvider.getConnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from usertable where Password='"+currentPassword+"'");
		while(rs.next()){
			email=rs.getString(6);
		pass=rs.getString(2);
	        } 
		if(pass.equals(currentPassword)){
		Statement st1=conn.createStatement();
		int i=st1.executeUpdate("update usertable set Password='"+Newpass+"' where Email='"+email+"'");
		out.println("<body bgcolor='brown'><center><h1>Password changed successfully</h1></center></body>");
		st1.close();
		conn.close();
		}
		else{
		out.println("<body bgcolor='brown'><center><h1>Invalid Current Password</h1></center></body>");
		}
		}
		catch(Exception e){
		e.printStackTrace();
		}
		
	}

}

