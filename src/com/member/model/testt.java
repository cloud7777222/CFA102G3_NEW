package com.member.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testt
 */
@WebServlet("/testt")
public class testt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO m=new MemberVO();
		MemberService m2=new MemberService();
		List<MemberVO> list=new ArrayList<MemberVO>();
//		FileInputStream fis=new FileInputStream("C:/test/dog.jpg");
//		byte[] b=new byte[fis.available()];
//		fis.read(b);
//		fis.close();
//		java.sql.Date d=new java.sql.Date(98, 8, 27);
		list=m2.getAllMember();
		System.out.println(list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
