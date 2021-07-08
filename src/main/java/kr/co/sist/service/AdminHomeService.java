package kr.co.sist.service;


import java.text.DecimalFormat;

import kr.co.sist.dao.AdminHomeDAO;

public class AdminHomeService {
	
	DecimalFormat df = new DecimalFormat("###,###");
	
	public String searchTodayBooking() {
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return ahDAO.selectTodayBooking();
	}//searchTodayBooking
	
	public String searchTodaySales(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return df.format(ahDAO.selectTodaySales());
	}//TodaySales
	
	public String searchTodayGuest(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return ahDAO.selectTodayGuest();
	}//TodayGuest
	
	public String searchTomorrowGuest(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return ahDAO.selectTomorrowGuest();
	}//TomorrowGuest
	
	public String searchThisMonthGuest(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return ahDAO.selectThisMonthGuest();
	}//ThisMonthGuest
	
	public String searchThisMonthSales(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return df.format(ahDAO.selectThisMonthSales());
	}//ThisMonthSales
	
	public String searchLastMonthGuest(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return ahDAO.selectLastMonthGuest();
	}//LastMonthGuest
	
	public String searchLastMonthSales(){
		AdminHomeDAO ahDAO = new AdminHomeDAO();
		return df.format(ahDAO.selectLastMonthSales()) ;
	}//LastMonthSales
	
}//class
