package kr.co.sist.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.vo.LoginVO;


public class AdminLoginDAO {
	
	public String selectAdminLogin(LoginVO lVO) {
		String adminId="";
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		adminId = ss.selectOne("kr.co.sist.loginMapper.selectLogin", lVO);
		
		if( ss != null ) { ss.close(); }//end if
		
		return adminId;
	}//selectAdminLogin

}//class
