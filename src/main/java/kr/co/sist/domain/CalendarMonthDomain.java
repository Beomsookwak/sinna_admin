package kr.co.sist.domain;

public class CalendarMonthDomain {
	
	private String bookingStatus, chkIn;

	public CalendarMonthDomain() {
	}

	public CalendarMonthDomain(String bookingStatus, String chkIn) {
		this.bookingStatus = bookingStatus;
		this.chkIn = chkIn;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getChkIn() {
		return chkIn;
	}

	public void setChkIn(String chkIn) {
		this.chkIn = chkIn;
	}
	
	
}
