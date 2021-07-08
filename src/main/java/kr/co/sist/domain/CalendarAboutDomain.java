package kr.co.sist.domain;

public class CalendarAboutDomain {
	
	private String chkIn, roomNo;

	public CalendarAboutDomain() {
	}

	public CalendarAboutDomain(String chkIn, String roomNo) {
		super();
		this.chkIn = chkIn;
		this.roomNo = roomNo;
	}

	public String getChkIn() {
		return chkIn;
	}

	public void setChkIn(String chkIn) {
		this.chkIn = chkIn;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

}
