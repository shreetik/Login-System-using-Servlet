
package com.login.servlets;


import com.login.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	 public static void sendEmail(final String userName, final String password, String toAddress,
	            String subject, String message) throws AddressException,
	            MessagingException {
	 
	        // sets SMTP server properties
		 Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(message);
	 
	        // sends the e-mail
	        Transport.send(msg);
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/csm","root","csmpl@123");
                        Connection conn = ConnectionProvider.getConnection();
			PreparedStatement ps=conn.prepareStatement("select Password,UserName from usertable where Email=?");
			 String st = request.getParameter("txtmail");
			ps.setString(1, st); 
			ResultSet rs = ps.executeQuery();
			String pass="",name="";
			while(rs.next()) {
				name=rs.getString(2);
				pass=rs.getString(1);
			}
			String subject="Hello Mr."+name+" Here is Your Password.";
			String message="Your Password is "+pass;
			sendEmail("shreetikkumar@gmail.com", "98610986109861098610", st, subject, message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}