package kr.co.sist.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.sist.dao.AdminNoticeDAO;
import kr.co.sist.domain.NoticeMainDomain;
import kr.co.sist.domain.NoticeOneDomain;
import kr.co.sist.vo.NoticeAddVO;
import kr.co.sist.vo.NoticeModifyVO;

public class AdminNoticeService {
	
	public JSONObject searchMainNotice(String text_search) {
		AdminNoticeDAO anDAO = new AdminNoticeDAO();
		JSONObject jsonObject = new JSONObject();
		
		List<NoticeMainDomain> list = anDAO.selectMainNotice(text_search);
		jsonObject.put("resultFlag",true);
			
			JSONArray jaNotice = new JSONArray();
			JSONObject jsonTemp = null;
			for( NoticeMainDomain nmDo : list ){
				jsonTemp = new JSONObject();
				jsonTemp.put("notice_no",nmDo.getNoticeNo() );
				jsonTemp.put("notice_class",nmDo.getNoticeClass() );
				jsonTemp.put("notice_title",nmDo.getNoticeTitle() );
				
				jaNotice.add( jsonTemp );
			}//end for
			jsonObject.put("data",jaNotice);
			jsonObject.put("dataCnt",jaNotice.size() );

		return jsonObject;
	}//searchMainNotice
	
	public NoticeOneDomain searchOneNotice(String notice_no) {
		AdminNoticeDAO anDAO = new AdminNoticeDAO();
		NoticeOneDomain nod = anDAO.selectOneNotice(notice_no);
		return nod;
	}//searchOneNotice
	
	public int modifyNotice(NoticeModifyVO nmVO) {
		int rowCnt = 0;
		
		AdminNoticeDAO anDAO = new AdminNoticeDAO();
		rowCnt = anDAO.updateNotice(nmVO);
		
		return rowCnt;
	}//modifyNotice
	
	public int addNotice(NoticeAddVO naVO) {
		int rowCnt = 0;
		
		AdminNoticeDAO anDAO = new AdminNoticeDAO();
		rowCnt = anDAO.insertNotice(naVO);
		
		return rowCnt;
	}//addNotice
	
	public int removeNotice(String noticeNo) {
		int rowCnt = 0;
		
		AdminNoticeDAO anDAO = new AdminNoticeDAO();
		rowCnt = anDAO.deleteNotice(noticeNo);
		
		return rowCnt;
	}//removeNotice
	
}//class
