package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.service.AdminRoomService;
import kr.co.sist.vo.RoomAddVO;
import kr.co.sist.vo.RoomModifyVO;


@Controller
public class AdminRoomController {
	
	/**
	 * main form
	 */
	@RequestMapping(value="/adminRoom/room_main_form", method = GET)
	public void roomMainFrm() {
	}//roomMainFrm
	
	/**
	 * 객실정보 전체 검색
	 * @param text_search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminRoom/use_search.do",method = {GET,POST} )
	public String roomSearchProcess( @RequestParam String text_search , Model model ) {
		AdminRoomService ars = new AdminRoomService();
		
		model.addAttribute("roomList",ars.searchMainRoom(text_search).toJSONString());
		
		return "adminRoom/search_json";
	}//roomSearchProcess
	
	
	/**
	 * 객실상세 정보
	 * @param room_no 선택된 객실 번호
	 * @param thisRoom 객실번호를 가져오기 위한 배열
	 * @param btnNo 객실번호를 가져오기 위한 배열
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminRoom/room_one.do",method = {GET,POST} )
	public String roomOneFrm( String room_no, String[] thisRoom, String[] btnNo, Model model ) {
		
		room_no =thisRoom[Integer.parseInt( btnNo[0] )]; //해당하는 객실 번호 가져오기
		
		AdminRoomService ars = new AdminRoomService();
		model.addAttribute("roomInfo",ars.searchOneRoom(room_no));
		
		return "adminRoom/room_one_form";
	}//roomOneFrm
	
	/**
	 * 객실 추가 Form
	 */
	@RequestMapping(value="/adminRoom/room_add_form.do",method = GET )
	public void roomAddFrm() {
		
	}//roomAddFrm
	
	/**
	 * 객실추가 process
	 * @param adVO
	 * @param model
	 * @param file_roomImg 선택된 이미지 파일 이름
	 * @param roomImg 선택된 파일이 없을때 기본 이미지 파일 이름
	 * @return
	 */
	@RequestMapping(value="/adminRoom/room_add.do",method = {GET,POST} )
	public String roomAddProcess( RoomAddVO adVO, Model model,
										String file_roomImg, String roomImg ) {
		
		String url = "http://211.63.89.133/sinna2_admin/common/images/room/";
		roomImg = url + file_roomImg;
		adVO.setRoomImg(roomImg);
		
		AdminRoomService ars = new AdminRoomService();
		model.addAttribute("rowCnt",ars.addRoom(adVO));
		
		return "adminRoom/room_add_result";
	}//roomAddProcess
	
	/**
	 * 객실정보 수정을 위한 method
	 * @param rmVO
	 * @param model
	 * @param file_roomImg 선택된 이미지 파일 이름
	 * @param hiden_roomImg 선택된 파일이 없을때 기본 이미지 파일 이름
	 * @return
	 */
	@RequestMapping(value="/adminRoom/room_modify.do",method = {GET,POST} )
	public String roomModifyProcess( RoomModifyVO rmVO, Model model,
										String file_roomImg, String hiden_roomImg ) {
		
		String url = "http://211.63.89.133/sinna2_admin/common/images/room/";
		String roomImg = url+file_roomImg;
		
		if( file_roomImg == null || file_roomImg.equals("") ) {
			roomImg = hiden_roomImg;
		}//end if
		
		rmVO.setRoomImg(roomImg);
		
		AdminRoomService ars = new AdminRoomService();
		model.addAttribute("rowCnt",ars.modifyRoom(rmVO));
		
		return "adminRoom/room_modify_result";
	}//roomModifyProcess
	
}//class
