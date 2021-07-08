package kr.co.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.domain.NoticeMainDomain;
import kr.co.sist.domain.NoticeOneDomain;
import kr.co.sist.vo.NoticeAddVO;
import kr.co.sist.vo.NoticeModifyVO;


public class AdminNoticeDAO {
	
	public List<NoticeMainDomain> selectMainNotice(String text_search){
		List<NoticeMainDomain> list = null;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		list= ss.selectList("kr.co.sist.noticeMapper.selectMainNotice",text_search) ;
		if( ss != null ) { ss.close(); }//end if
		return list;
	}//selectMainNotice
	
	public NoticeOneDomain selectOneNotice(String notice_no){
		NoticeOneDomain nod = new NoticeOneDomain();
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		nod= ss.selectOne("kr.co.sist.noticeMapper.selectOneNotice", notice_no) ;
		if( ss != null ) { ss.close(); }//end if
		return nod;
	}//selectOneNotice
	
	public int updateNotice(NoticeModifyVO nmVO) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.noticeMapper.updateNotice", nmVO);
		
		//commit
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//updateNotice
	
	public int insertNotice(NoticeAddVO naVO) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.noticeMapper.insertNotice", naVO);
		
		//commit
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//insertNotice
	
	public int deleteNotice(String noticeNo) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.noticeMapper.deleteNotice", noticeNo);
		
		//commit
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//deleteNotice
	

}//class
