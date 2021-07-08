package kr.co.sist.dao;

import org.apache.ibatis.session.SqlSession;


public class AdminHomeDAO {
	
	public String selectTodayBooking(){
		//List<ReservationDomain> list = null;
		String result="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result= ss.selectOne("kr.co.sist.homeMapper.todayBooking") ;
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//selectTodayBooking
	
	public int selectTodaySales(){
		int result=0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result= ss.selectOne("kr.co.sist.homeMapper.todaySales") ;
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//selectTodaySales
	
	public String selectTodayGuest(){
		String result="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.todayGuest");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//selectTodayGuest
	
	public String selectTomorrowGuest(){
		String result="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.tomorrowGuest");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//tomorrowGuest
	
	public String selectThisMonthGuest(){
		String result="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.thisMonthGuest");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//thisMonthGuest
	
	public int selectThisMonthSales(){
		int result=0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.thisMonthSales");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//thisMonthSales
	
	public String selectLastMonthGuest(){
		String result="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.lastMonthGuest");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//lastMonthGuest
	
	public int selectLastMonthSales(){
		int result=0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		result=ss.selectOne("kr.co.sist.homeMapper.lastMonthSales");
		if( ss != null ) { ss.close(); }//end if
		return result;
	}//lastMonthSales
	
}//class
