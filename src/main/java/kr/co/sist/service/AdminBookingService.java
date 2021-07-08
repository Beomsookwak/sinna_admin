package kr.co.sist.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.sist.dao.AdminBookingDAO;
import kr.co.sist.domain.BookingMainDomain;
import kr.co.sist.domain.BookingOneDomain;
import kr.co.sist.vo.BookingModifyVO;

public class AdminBookingService {
	
	public JSONObject searchMainBooking(String text_search) {
		AdminBookingDAO abDAO = new AdminBookingDAO();
		JSONObject jsonObject = new JSONObject();
		
		List<BookingMainDomain> list = abDAO.selectMainBooking(text_search);
		jsonObject.put("resultFlag",true);
			
			JSONArray jaBooking = new JSONArray();
			JSONObject jsonTemp = null;
			for( BookingMainDomain bmDo : list ){
				jsonTemp = new JSONObject();
				jsonTemp.put("booking_no",bmDo.getBookingNo());
				jsonTemp.put("member_name",bmDo.getMemberName());
				jsonTemp.put("tel",bmDo.getTel());
				jsonTemp.put("birth",bmDo.getBirth());
				jsonTemp.put("chk_in",bmDo.getChkIn());
				jsonTemp.put("chk_out",bmDo.getChkOut());
				jsonTemp.put("room_name",bmDo.getRoomName());
				jsonTemp.put("booking_status",bmDo.getBookingStatus());
				
				jaBooking.add( jsonTemp );
			}//end for
			jsonObject.put("data",jaBooking);
			jsonObject.put("dataCnt",jaBooking.size() );

		return jsonObject;
	}//searchMainRoom
	
	public BookingOneDomain searchOneBooking(String booking_id) {
		AdminBookingDAO abDAO = new AdminBookingDAO();
		BookingOneDomain bod = abDAO.selectOneBooking(booking_id);
		return bod;
	}//searchOneBooking
	
	public int modifyBooking(BookingModifyVO bmVO) {
		int rowCnt = 0;
		
		AdminBookingDAO abDAO = new AdminBookingDAO();
		rowCnt = abDAO.updateBooking(bmVO);
		
		return rowCnt;
	}//modifyBooking
	
	
	
}//class
