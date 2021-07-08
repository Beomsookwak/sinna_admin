package kr.co.sist.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.domain.CalendarAboutDomain;
import kr.co.sist.domain.CalendarMonthDomain;


public class AdminCalendarDAO {
	
	public String selectCalendarMonth( CalendarMonthDomain cmDomain ) {
		String countMonth = "";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		countMonth = ss.selectOne("kr.co.sist.calendarMapper.selectCalendarMonth",cmDomain);
		
		if( ss != null ) { ss.close(); }//end if
		
		return countMonth;
	}//selectCalendarMonth
	
	public String selectCalendarAbout( CalendarAboutDomain caDomain ) {
		String statusAbout = "";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		if( ss.selectOne("kr.co.sist.calendarMapper.selectCalendarAbout",caDomain) == null ) {
			statusAbout = "";
		}else {
			statusAbout = ss.selectOne("kr.co.sist.calendarMapper.selectCalendarAbout",caDomain);
		}//end else
		
		if( ss != null ) { ss.close(); }//end if
		
		return statusAbout;
	}//selectCalendarAbout
	
}//class
