package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.sist.service.AdminLoginService;
import kr.co.sist.vo.LoginVO;

@Controller
@SessionAttributes("loginId") //���ǿ� �Ҵ��� ���� n���� ��� �迭�� ���
public class AdminLoginController {

	/**
	 * �α��� Form
	 */
	@RequestMapping(value="/adminLogin/admin_login_form.do", method = {GET,POST})
	public void loginFrm() {
	}//loginFrm
	
	/**
	 * ������ �̿��� �α��� Process
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminLogin/admin_login.do", method = {GET,POST})
	public String loginProcess(LoginVO lVO, Model model) {
		String loginReturn = "";
		
		AdminLoginService als = new AdminLoginService();
		
		if( als.searchAdminLogin(lVO)==null ) { //id�� pass�� ��ġ���� ���� ��
			loginReturn = "adminLogin/admin_login_form";
			model.addAttribute("loginFail","���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���." );
		}else {//id�� pass�� ��ġ�� ��
			model.addAttribute("loginId",als.searchAdminLogin(lVO) );
			loginReturn = "redirect:/adminHome/admin_home_form.do";
		}//end else
		
		return loginReturn;
	}//loginProcess
	
	/**
	 * ������ �̿��� �α׾ƿ� Process
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="/adminLogin/admin_logout.do", method = {GET,POST})
	public String logoutProcess(SessionStatus ss) {
		ss.setComplete(); //���� �ʱ�ȭ
		return "adminLogin/admin_login_form";
	}//logoutProcess
	
}
