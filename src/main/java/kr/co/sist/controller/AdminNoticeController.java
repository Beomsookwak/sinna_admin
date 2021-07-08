package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.service.AdminNoticeService;
import kr.co.sist.vo.NoticeAddVO;
import kr.co.sist.vo.NoticeModifyVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminNoticeController {
	
	/**
	 * Main Form
	 */
	@RequestMapping(value="/adminNotice/notice_main_form.do",method = GET )
	public void noticeMainFrm() {
	}//noticeMainFrm
	
	/**
	 * �������� ��ü�˻� process
	 * @param text_search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminNotice/use_search.do",method = {GET,POST} )
	public String noticeSearchProcess( @RequestParam String text_search, Model model ) {
		AdminNoticeService ans  = new AdminNoticeService();
		
		model.addAttribute("noticeList",ans.searchMainNotice(text_search).toJSONString());
		
		return "adminNotice/search_json";
	}//noticeSearchProcess
	
	/**
	 * ������ ���� Form
	 * @param notice_no ���õ� �������� ��ȣ
	 * @param thisNotice �������� ��ȣ�� ������������ �迭
	 * @param btnNo �������� ��ȣ�� ������������ �迭
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminNotice/notice_one.do",method = {GET,POST} )
	public String noticeOneFrm( String notice_no, String[] thisNotice, String[] btnNo, Model model ) {
		
		notice_no = thisNotice[Integer.parseInt( btnNo[0] )]; //�ش��ϴ� �۹�ȣ ��������
		
		AdminNoticeService ans = new AdminNoticeService();
		model.addAttribute("noticeInfo",ans.searchOneNotice(notice_no));
		
 		return "adminNotice/notice_one_form";
	}//noticeOneFrm
	
	/**
	 * �������� �߰� form
	 */
	@RequestMapping(value="/adminNotice/notice_add_form.do",method = {GET} )
	public void noticeAddFrm() {
	}//noticeAddFrm
	
	/**
	 * �������� �߰� Process
	 * @param naVO
	 * @param model
	 * @param file_noticeImg ���õ� �̹��� ���� �̸�
	 * @param noticeImg ���õ� ������ ������ �⺻ �̹��� ���� �̸�
	 * @return
	 */
	@RequestMapping(value="/adminNotice/notice_add.do",method = {GET,POST} )
	public String noticeAddProcess( NoticeAddVO naVO, Model model,
											String file_noticeImg, String noticeImg) {
		
		String url = "http://211.63.89.133/sinna2_admin/common/images/notice/";
		noticeImg = url + file_noticeImg;
		naVO.setNoticeImg(noticeImg);
		
		AdminNoticeService ans = new AdminNoticeService();
		model.addAttribute("rowCnt",ans.addNotice(naVO));
		
		return "adminNotice/notice_add_result";
	}//noticeAddProcess
	
	/**
	 * �������� ���� process
	 * @param nmVO
	 * @param model
	 * @param file_noticeImg ���õ� �̹��� ���� �̸�
	 * @param hidden_noticeImg ���õ� ������ ������ �⺻ �̹��� ���� �̸�
	 * @return
	 */
	@RequestMapping(value="/adminNotice/notice_modify.do",method = {GET,POST} )
	public String noticeModifyProcess( NoticeModifyVO nmVO, Model model,
												String file_noticeImg, String hidden_noticeImg ) {
		String url = "http://211.63.89.133/sinna2_admin/common/images/notice/";
		String noticeImg = url + file_noticeImg;
		
		if( file_noticeImg == null || file_noticeImg.equals("") ) {
			noticeImg = hidden_noticeImg;
		}//end if
		
		nmVO.setNoticeImg(noticeImg);
		
		AdminNoticeService ans = new AdminNoticeService();
		model.addAttribute("rowCnt",ans.modifyNotice(nmVO));
		
		return "adminNotice/notice_modify_result";
	}//noticeModifyProcess
	
	/**
	 * �������� ���� process
	 * @param noticeNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminNotice/notice_remove.do",method = {GET,POST} )
	public String noticeRemoveProcess(String noticeNo, Model model) {
		
		AdminNoticeService ans = new AdminNoticeService();
		model.addAttribute("rowCnt",ans.removeNotice(noticeNo));
		
		return "adminNotice/notice_remove_result";
	}//noticeRemoveProcess
	
}//class
