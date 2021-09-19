
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

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
	try 
        {  

            Connection conn = ConnectionProvider.getConnection();
            String add=request.getParameter("txtadd");
            String ph=request.getParameter("txtphone");
            String email=request.getParameter("txtemail");
            String gender=request.getParameter("txtgender");
            String dob=request.getParameter("txtdob");
    		
    	
    		PreparedStatement ps = conn.prepareStatement("update usertable set  Address=(?), Phone=(?),Email=(?),Gender=(?),DOB=(?)");
     
                ps.setString(1, add);
                ps.setString(2, ph);
                ps.setString(3, email);
                ps.setString(4, gender);
                ps.setString(5, dob);
              
                ps.executeUpdate();
			

                   pw.print("Update Successfully");
             
           
		} catch (Exception e) {
			e.printStackTrace();
                        pw.print(e);
		}
	}

}
