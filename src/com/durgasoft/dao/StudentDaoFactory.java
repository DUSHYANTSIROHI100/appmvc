package com.durgasoft.dao;

public class StudentDaoFactory {

	private static StudentDao studentDao;
	static {
		studentDao=new StudentDaoImpl();
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
