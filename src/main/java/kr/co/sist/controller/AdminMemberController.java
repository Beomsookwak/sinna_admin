package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.service.AdminMemberService;

@Controller
public class AdminMemberController {
	
	/**
	 * Main Form
	 */
	@RequestMapping(value="/adminMember/member_main_form.do",method = GET )
	public void memberMainFrm() {
	}//memberMainFrm
	
	/**
	 * 회원정보 전체 검색
	 * @param text_search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminMember/use_search.do",method =  {GET,POST} )
	public String memberSearchProcess( @RequestParam String text_search, Model model ) {
		AdminMemberService ams = new AdminMemberService();
		
		model.addAttribute("memberList",ams.searchMainMember(text_search).toJSONString());
	
		return "adminMember/search_json";
	}//memberMainFrm
	
	/**
	 * 회원상세 정보
	 * @param member_id 선택된 회원 아이디
	 * @param thisBooking 회원 아이디를 가져오기 위한 배열
	 * @param btnNo 회원 아이디를 가져오기 위한 배열
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminMember/member_one.do",method = {GET,POST} )
	public String memberOneFrm( String member_id, String[] thisMember, String[] btnNo, Model model ) {
		
		member_id =thisMember[Integer.parseInt( btnNo[0] )]; //해당하는 회원 번호 가져오기
		
		AdminMemberService ams = new AdminMemberService();
		model.addAttribute("memberInfo",ams.searchOneMember(member_id));
		
		return "adminMember/member_one_form";
	}//memberOneFrm
	
	
	/**
	 * 회원삭제 Process
	 * @param memberId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminMember/member_remove.do",method = {GET,POST} )
	public String memberDeleteProcess( String memberId, Model model ) {
		
		AdminMemberService ams = new AdminMemberService();
		model.addAttribute("rowCnt",ams.removeMember(memberId));
		
		return "adminMember/member_remove_result";
	}//memberDeleteProcess
	
}//class
