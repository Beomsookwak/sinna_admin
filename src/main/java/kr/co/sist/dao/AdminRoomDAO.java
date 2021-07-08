package kr.co.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.domain.RoomMainDomain;
import kr.co.sist.domain.RoomOneDomain;
import kr.co.sist.vo.RoomAddVO;
import kr.co.sist.vo.RoomModifyVO;


public class AdminRoomDAO {
	
	public List<RoomMainDomain> selectMainRoom(String text_search){
		List<RoomMainDomain> list = null;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		list= ss.selectList("kr.co.sist.roomMapper.selectMainRoom",text_search) ;
		if( ss != null ) { ss.close(); }//end if
		return list;
	}//selectMainRoom
	
	public RoomOneDomain selectOneRoom(String room_no){
		RoomOneDomain rod = new RoomOneDomain();
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		
		rod= ss.selectOne("kr.co.sist.roomMapper.selectOneRoom", room_no) ;
		if( ss != null ) { ss.close(); }//end if
		return rod;
	}//selectOneRoom
	
	public int updateRoom(RoomModifyVO rmVO) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.roomMapper.updateRoom", rmVO);
		
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//updateRoom
	
	public int insertRoom(RoomAddVO raVO) {
		int rowCnt =0;
		
		SqlSession ss = MyBatisHandler.getInstance().getHandler();
		rowCnt = ss.update("kr.co.sist.roomMapper.insertRoom", raVO);
		
		if(rowCnt == 1) {
			ss.commit();
		}//end if
		
		if( ss != null ) { ss.close(); }//end if
		
		return rowCnt;
	}//insertRoom
	
}//class
