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
@SessionAttributes("loginId") //세션에 할당할 값이 n개인 경우 배열을 사용
public class AdminLoginController {

	/**
	 * 로그인 Form
	 */
	@RequestMapping(value="/adminLogin/admin_login_form.do", method = {GET,POST})
	public void loginFrm() {
	}//loginFrm
	
	/**
	 * 세션을 이용한 로그인 Process
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminLogin/admin_login.do", method = {GET,POST})
	public String loginProcess(LoginVO lVO, Model model) {
		String loginReturn = "";
		
		AdminLoginService als = new AdminLoginService();
		
		if( als.searchAdminLogin(lVO)==null ) { //id와 pass가 일치하지 않을 때
			loginReturn = "adminLogin/admin_login_form";
			model.addAttribute("loginFail","아이디 또는 비밀번호를 확인해주세요." );
		}else {//id와 pass가 일치할 때
			model.addAttribute("loginId",als.searchAdminLogin(lVO) );
			loginReturn = "redirect:/adminHome/admin_home_form.do";
		}//end else
		
		return loginReturn;
	}//loginProcess
	
	/**
	 * 세션을 이용한 로그아웃 Process
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="/adminLogin/admin_logout.do", method = {GET,POST})
	public String logoutProcess(SessionStatus ss) {
		ss.setComplete(); //세션 초기화
		return "adminLogin/admin_login_form";
	}//logoutProcess
	
}
