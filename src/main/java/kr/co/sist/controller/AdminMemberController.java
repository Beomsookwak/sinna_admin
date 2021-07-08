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
	 * ȸ������ ��ü �˻�
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
	 * ȸ���� ����
	 * @param member_id ���õ� ȸ�� ���̵�
	 * @param thisBooking ȸ�� ���̵� �������� ���� �迭
	 * @param btnNo ȸ�� ���̵� �������� ���� �迭
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminMember/member_one.do",method = {GET,POST} )
	public String memberOneFrm( String member_id, String[] thisMember, String[] btnNo, Model model ) {
		
		member_id =thisMember[Integer.parseInt( btnNo[0] )]; //�ش��ϴ� ȸ�� ��ȣ ��������
		
		AdminMemberService ams = new AdminMemberService();
		model.addAttribute("memberInfo",ams.searchOneMember(member_id));
		
		return "adminMember/member_one_form";
	}//memberOneFrm
	
	
	/**
	 * ȸ������ Process
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
