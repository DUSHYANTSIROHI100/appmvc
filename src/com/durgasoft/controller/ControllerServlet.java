package com.durgasoft.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.durgasoft.beans.Student;
import com.durgasoft.factory.StudentServiceFactory;
import com.durgasoft.service.StudentService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String request_URI = request.getRequestURI();
	System.out.println(request_URI);
	RequestDispatcher requestDispatcher=null;
	if(request_URI.endsWith("addform.do")) {
		requestDispatcher = request.getRequestDispatcher("/addform.html");
		requestDispatcher.forward(request, response);
	}
	
	if(request_URI.endsWith("searchform.do")) {
		requestDispatcher = request.getRequestDispatcher("/searchform.html");
		requestDispatcher.forward(request, response);
	}
	
	if(request_URI.endsWith("updateform.do")) {
		requestDispatcher = request.getRequestDispatcher("/updateform.html");
		requestDispatcher.forward(request, response);
	}
	
	if(request_URI.endsWith("deleteform.do")) {
		requestDispatcher = request.getRequestDispatcher("/deleteform.html");
		requestDispatcher.forward(request, response);
		
	}
	if(request_URI.endsWith("add.do")) {
		
	String sid=request.getParameter("sid");
	String sname=request.getParameter("sname");
	String saddr=request.getParameter("saddr");
		Student student = new Student();
		student.setSid(sid);
		student.setSname(sname);
		student.setSaddr(saddr);
		
		StudentService studentService = StudentServiceFactory.getStudentService();
		String status = studentService.addStudent(student);
		
		if(status.equals("existed")) {
			requestDispatcher = request.getRequestDispatcher("existed.html");
			requestDispatcher.forward(request, response);
		}
		if(status.equals("success")) {
			requestDispatcher = request.getRequestDispatcher("success.html");
			requestDispatcher.forward(request, response);
		}
		if(status.equals("failure")) {
			requestDispatcher = request.getRequestDispatcher("failure.html");
			requestDispatcher.forward(request, response);
		}
	}
	if(request_URI.endsWith("search.do")) {
		String sid=request.getParameter("sid");
		StudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(sid);
		if(student==null) {
			requestDispatcher=request.getRequestDispatcher("notexisted.html");
			requestDispatcher.forward(request, response);
		}else {
			
		
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<body bgcolor='lightblue'>");
			out.println("<br><br><br>");
			out.println("<table align='center' border='1'>");
			out.println("<tr><td>Student Id</td><td>"+student.getSid()+"</td></tr>");
			out.println("<tr><td>Student Name</td><td>"+student.getSname()+"</td></tr>");
			out.println("<tr><td>Student Saddr</td><td>"+student.getSaddr()+"</td></tr>");
			
			out.println("</table></body></html>");
		}
	}
	if(request_URI.endsWith("editform.do")) {
		String sid=request.getParameter("sid");
		StudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(sid);
		if(student==null) {
			requestDispatcher=request.getRequestDispatcher("notexisted.html");
			requestDispatcher.forward(request, response);
		}else {
			
		
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<body bgcolor='lightblue'>");
			out.println("<br><br><br>");
			out.println("<form method='POST' action='update.do'>");
			out.println("<table align='center' border='1'>");
			out.println("<tr><td>Student Id</td><td>"+student.getSid()+"</td></tr>");
			out.println("<input type='hidden' name='sid' value='"+student.getSid()+"'/>");
			out.println("<tr><td>Student Name</td><td><input type='text' name='sname' value='"+student.getSid()+"'/></td></tr>");
			out.println("<tr><td>Student Address</td><td><input type='text' name='saddr' value='"+student.getSaddr()+"'/></td></tr>");
			out.println("<tr><td><input type='submit' value='update'/></td></tr>");
			out.println("</table></form></body></html>");
		}
	}
	if(request_URI.endsWith("update.do")) {
		
		String sid=request.getParameter("sid");
		String sname=request.getParameter("sname");
		String saddr=request.getParameter("saddr");
			Student student = new Student();
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.updateStudent(student);
			
			if(status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
				else {
				requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
				}
			}
	if(request_URI.endsWith("delete.do")) {
		String sid=request.getParameter("sid");
		StudentService studentService = StudentServiceFactory.getStudentService();
		String status = studentService.deleteStudent(sid);
		if(status.equals("success")) {
			requestDispatcher=request.getRequestDispatcher("success.html");
			requestDispatcher.forward(request, response);
		}
		if(status.equals("failure")) {
			requestDispatcher = request.getRequestDispatcher("failure.html");
			requestDispatcher.forward(request, response);
		}
		if(status.equals("notexisted")) {
			requestDispatcher = request.getRequestDispatcher("notexisted.html");
			requestDispatcher.forward(request, response);
		}
		}
	}
}
	
		


