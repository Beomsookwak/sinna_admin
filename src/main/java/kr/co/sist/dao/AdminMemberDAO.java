package kr.co.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.domain.MemberMainDomain;
import kr.co.sist.domain.MemberOneDomain;


public class AdminMemberDAO {
	
	public List<MemberMainDomain> selectMainMember(String text_search){
		List<MemberMainDomain> list = null;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		list= ss.selectList("kr.co.sist.memberMapper.selectMainMember",text_search) ;
		if( ss != null ) { ss.close(); }//end if
		return list;
	}//selectMainMember
	
	public MemberOneDomain selectOneMember(String member_id){
		MemberOneDomain mod = new MemberOneDomain();
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		mod= ss.selectOne("kr.co.sist.memberMapper.selectOneMember", member_id) ;
		if( ss != null ) { ss.close(); }//end if
		return mod;
	}//selectOneMember
	
	public int deleteMember(String memberId) {
		int rowCnt = 0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.delete("kr.co.sist.memberMapper.deleteMember", memberId);
		
		//commit
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//deleteMember

}//class
