package kr.co.sist.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.sist.dao.AdminMemberDAO;
import kr.co.sist.dao.AdminNoticeDAO;
import kr.co.sist.domain.MemberMainDomain;
import kr.co.sist.domain.MemberOneDomain;

public class AdminMemberService {
	
	public JSONObject searchMainMember(String text_search) {
		AdminMemberDAO amDAO = new AdminMemberDAO();
		JSONObject jsonObject = new JSONObject();
		
		List<MemberMainDomain> list = amDAO.selectMainMember(text_search);
		jsonObject.put("resultFlag",true);
			
			JSONArray jaMember = new JSONArray();
			JSONObject jsonTemp = null;
			for( MemberMainDomain mmDo : list ){
				jsonTemp = new JSONObject();
				jsonTemp.put("member_id",mmDo.getMemberId());
				jsonTemp.put("email",mmDo.getEmail());
				jsonTemp.put("member_name",mmDo.getMemberName());
				jsonTemp.put("birth",mmDo.getBirth());
				jsonTemp.put("tel",mmDo.getTel());
				
				jaMember.add( jsonTemp );
			}//end for
			jsonObject.put("data",jaMember);
			jsonObject.put("dataCnt",jaMember.size() );

		return jsonObject;
	}//searchMainRoom
	
	public MemberOneDomain searchOneMember(String member_id) {
		AdminMemberDAO amDAO = new AdminMemberDAO();
		MemberOneDomain mod = amDAO.selectOneMember(member_id);
		return mod;
	}//searchOneMember
	
	public int removeMember(String memberId) {
		int rowCnt = 0;
		
		AdminMemberDAO amDAO = new AdminMemberDAO();
		rowCnt = amDAO.deleteMember(memberId);
		
		return rowCnt;
	}//removeMember
	
}//class
