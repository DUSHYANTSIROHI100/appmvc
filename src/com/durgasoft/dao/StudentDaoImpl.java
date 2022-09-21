package com.durgasoft.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import com.durgasoft.beans.Student;
import com.durgasoft.factory.ConnectionFactory;


public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student student) {
		// TODO Auto-generated method stub
		String status="";
		try {
			Student std= search(student.getSid());
			if(std==null) {
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				int rowCount=st.executeUpdate("insert into student values('"+student.getSid()+"','"+student.getSname()+"','"+student.getSaddr()+"')");
				if(rowCount==1) {
					status="success";
				}else {
					status="failure";
				}
			}else {
				status="failure";
				status="existed";
			}
		}catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		// TODO Auto-generated method stub
		Student student =null;
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=  st.executeQuery("select * from student where SID='"+sid+"'");
			boolean b = rs.next();
			if(b==true) {
				student=new Student();
				student.setSid(rs.getString("SID"));
				student.setSname(rs.getString("SNAME"));
				student.setSaddr(rs.getString("SADDR"));
			}else {
				student=null;
			}
		}
		catch (Exception e) {
		e.printStackTrace();	
		}
		return student;
	}

	@Override
	public String update(Student student) {
		// TODO Auto-generated method stub
		String status="";
		try {
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				int rowCount=st.executeUpdate("update student set SNAME='"+student.getSname()+"', SADDR='"+student.getSaddr()+"' Where SID='"+student.getSid()+"'");
				if(rowCount==1) {
					status="success";
				}
			else {
				status="failure";
			}
	
		}catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
		}
	

	@Override
	public String delete(String sid) {
		// TODO Auto-generated method stub
		String status="";
		try {
			Student std= search(sid);
			if(std==null) {
				status="notexisted";
			}
			else {
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				int rowCount=st.executeUpdate("delete from student Where SID='"+sid+"'");
				if(rowCount==1) {
					status="success";
				}else {
					status="failure";
				}
			}
		}catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}
	}


