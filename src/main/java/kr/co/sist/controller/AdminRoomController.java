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
	 * �������� ��ü �˻�
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
	 * ���ǻ� ����
	 * @param room_no ���õ� ���� ��ȣ
	 * @param thisRoom ���ǹ�ȣ�� �������� ���� �迭
	 * @param btnNo ���ǹ�ȣ�� �������� ���� �迭
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminRoom/room_one.do",method = {GET,POST} )
	public String roomOneFrm( String room_no, String[] thisRoom, String[] btnNo, Model model ) {
		
		room_no =thisRoom[Integer.parseInt( btnNo[0] )]; //�ش��ϴ� ���� ��ȣ ��������
		
		AdminRoomService ars = new AdminRoomService();
		model.addAttribute("roomInfo",ars.searchOneRoom(room_no));
		
		return "adminRoom/room_one_form";
	}//roomOneFrm
	
	/**
	 * ���� �߰� Form
	 */
	@RequestMapping(value="/adminRoom/room_add_form.do",method = GET )
	public void roomAddFrm() {
		
	}//roomAddFrm
	
	/**
	 * �����߰� process
	 * @param adVO
	 * @param model
	 * @param file_roomImg ���õ� �̹��� ���� �̸�
	 * @param roomImg ���õ� ������ ������ �⺻ �̹��� ���� �̸�
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
	 * �������� ������ ���� method
	 * @param rmVO
	 * @param model
	 * @param file_roomImg ���õ� �̹��� ���� �̸�
	 * @param hiden_roomImg ���õ� ������ ������ �⺻ �̹��� ���� �̸�
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
