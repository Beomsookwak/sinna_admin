package kr.co.sist.service;

import kr.co.sist.dao.AdminLoginDAO;
import kr.co.sist.vo.LoginVO;

public class AdminLoginService {
	
	public String searchAdminLogin(LoginVO lVO) {
		AdminLoginDAO alDAO = new AdminLoginDAO();
		String adminId=alDAO.selectAdminLogin(lVO);
		return adminId;
	}//searchAdminLogin
	
}//class
