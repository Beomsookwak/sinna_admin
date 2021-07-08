package kr.co.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.domain.BookingMainDomain;
import kr.co.sist.domain.BookingOneDomain;
import kr.co.sist.vo.BookingModifyVO;


public class AdminBookingDAO {
	
	public List<BookingMainDomain> selectMainBooking(String text_search){
		List<BookingMainDomain> list = null;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		list= ss.selectList("kr.co.sist.bookingMapper.selectMainBooking",text_search) ;
		if( ss != null ) { ss.close(); }//end if
		return list;
	}//selectMainBooking
	
	public BookingOneDomain selectOneBooking(String booking_no){
		BookingOneDomain bod = new BookingOneDomain();
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		bod= ss.selectOne("kr.co.sist.bookingMapper.selectOneBooking", booking_no) ;
		if( ss != null ) { ss.close(); }//end if
		return bod;
	}//selectOneBooking
	
	public int updateBooking(BookingModifyVO bmVO) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.bookingMapper.updateBooking", bmVO);
		
		//commit
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//updateBooking

}//class
