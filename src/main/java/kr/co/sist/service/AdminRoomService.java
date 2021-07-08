package kr.co.sist.service;

import java.text.DecimalFormat;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.sist.dao.AdminRoomDAO;
import kr.co.sist.domain.RoomMainDomain;
import kr.co.sist.domain.RoomOneDomain;
import kr.co.sist.vo.RoomAddVO;
import kr.co.sist.vo.RoomModifyVO;

public class AdminRoomService {
	
	DecimalFormat df = new DecimalFormat("###,###");
	
	public JSONObject searchMainRoom(String text_search) {
		AdminRoomDAO arDAO = new AdminRoomDAO();
		JSONObject jsonObject = new JSONObject();
		
		List<RoomMainDomain> list = arDAO.selectMainRoom(text_search);
		jsonObject.put("resultFlag",true);
			
			JSONArray jaRoom = new JSONArray();
			JSONObject jsonTemp = null;
			for( RoomMainDomain rmDo : list ){
				jsonTemp = new JSONObject();
				jsonTemp.put("room_no",rmDo.getRoomNo());
				jsonTemp.put("room_name",rmDo.getRoomName());
				jsonTemp.put("room_area",rmDo.getRoomArea());
				jsonTemp.put("room_people",rmDo.getRoomPeople());
				jsonTemp.put("room_price",df.format(rmDo.getRoomPrice()));
				
				jaRoom.add( jsonTemp );
			}//end for
			jsonObject.put("data",jaRoom);
			jsonObject.put("dataCnt",jaRoom.size() );

		return jsonObject;
	}//searchMainRoom
	
	public RoomOneDomain searchOneRoom(String room_no) {
		AdminRoomDAO arDAO = new AdminRoomDAO();
		RoomOneDomain rod = arDAO.selectOneRoom(room_no);
		return rod;
	}//searchOneRoom
	
	public int modifyRoom(RoomModifyVO rmVO) {
		int rowCnt = 0;
		
		AdminRoomDAO arDAO = new AdminRoomDAO();
		rowCnt = arDAO.updateRoom(rmVO);
		
		return rowCnt;
	}//modifyRoom
	
	public int addRoom(RoomAddVO raVO) {
		int rowCnt = 0;
		
		AdminRoomDAO arDAO = new AdminRoomDAO();
		rowCnt = arDAO.insertRoom(raVO);
		
		return rowCnt;
	}//addRoom
	
}//class
