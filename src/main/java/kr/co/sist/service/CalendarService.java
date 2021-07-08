package kr.co.sist.service;

import kr.co.sist.dao.AdminCalendarDAO;
import kr.co.sist.domain.CalendarAboutDomain;
import kr.co.sist.domain.CalendarMonthDomain;

public class CalendarService {
	
	/**
	 * �⵵�� �Ѱܹ޾� ����/ ����� �Ǵ��� �����̸� true, ����̸� false�� �����ϴ� �޼���
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 ==0) && (year % 100 !=0) ||(year % 400 ==0);
	}//isLeapYear
	
	/**
	 * ��, ���� �Ѱܹ޾� �� ���� ������ ��¥�� �����ϴ� �޼���
	 * @param year
	 * @param month
	 * @return
	 */
	public static int lastDay(int year ,int month ) {
		int[] m = {31,28,31,30,31,30,31,31,30,31,30,31};
		m[1]=isLeapYear(year)? 29:28;
		return m[month-1];
	}//lastDay
	
	/**
	 * ��, ��, ���� ��ܹ޾� 1�� 1�� 1�Ϻ��� ������ ��¥�� �հ踦 �����ϴ� �޼���
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int totalDay(int year, int month, int day) {
		int sum = (year-1)*365 +(year-1)/4 - (year-1)/100 +(year-1)/400;
		for (int i = 1; i < month; i++) {
			sum += lastDay(year,i);
		}//end for
		return sum + day;
	}//totalDay
	
	/**
	 * ��, ��, ���� �Ѱܹ޾� ������ ���ڷ� �����ϴ� �޼���, �Ͽ���(0),������(1)....�����(6)
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}//weekDay
	
	/**
	 * ���� ���а� �� �ٲ����� ������� td�� �����ϴ� �޼���
	 * @param year
	 * @param month
	 * @return
	 */
	public String dayOfWeek( int year, int month ) {
		
		AdminCalendarDAO acDAO = new AdminCalendarDAO();
		String yearMonth = year+"-"+String.format("%02d", month) +"-";
		

		String td ="";
		String ok = "http://211.63.89.133/sinna2_admin/common/images/cal/ok_cal.png";
		String wait = "http://211.63.89.133/sinna2_admin/common/images/cal/wait_cal.png";
		String cancle = "http://211.63.89.133/sinna2_admin/common/images/cal/cancle_cal.png";
		String deluxe = "R_00000001";
		String suite = "R_00000002";
		
		 for(int i = 1; i <= CalendarService.lastDay(year, month); i++){
		  //���Ϻ��� ���� �ٸ��� ���ֱ����� td�� class �±װɾ��ֱ� 
			CalendarAboutDomain caDomainDeluxe = new CalendarAboutDomain(yearMonth+String.format("%02d", i),deluxe);
			CalendarAboutDomain caDomainSuite = new CalendarAboutDomain(yearMonth+String.format("%02d", i),suite);

			 switch(CalendarService.weekDay(year, month, i)){
				case 0 : //�Ͽ���
					td += "<td class ='sun'>" +i+"<br/>";
					break;
				case 6 : //�����
					td += "<td class ='sat'>" +i+"<br/>";
					break;
				default : //����
					td += "<td class ='etc'>" +i+"<br/>";
					break;
			}//end switch
	 		if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("Y") ) {
	 			td += "<span class='cal_result'>�𷰽� : <img src='"+ok+"'/></span><br/>";
	 		}else if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("C") ){
	 			td += "<span class='cal_result'>�𷰽� : <img src='"+wait+"'/></span><br/>";
	 		}else if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("N") ){
	 			td +=  "<span class='cal_result'>�𷰽� : <img src='"+cancle+"'/></span><br/>";
	 		}else{
	 			td += "<span class='cal_result'>�𷰽� : </span><br/>";
	 		}//end if
			
			if( acDAO.selectCalendarAbout( caDomainSuite ).equals("Y") ) {
				td += "<span class='cal_result'>����Ʈ : <img src='"+ok+"'/></span><br/>";
			}else if( acDAO.selectCalendarAbout( caDomainSuite ).equals("C") ){
				td += "<span class='cal_result'>����Ʈ : <img src='"+wait+"'/></span><br/>";
			}else if( acDAO.selectCalendarAbout( caDomainSuite ).equals("N") ){
				td +=  "<span class='cal_result'>����Ʈ : <img src='"+cancle+"'/></span><br/>";
			}else{
				td += "<span class='cal_result'>����Ʈ : </span><br/>";
			}//end if
		 	
		 	// ����� ��¥(i)�� ������̰� �״��� ������ ��¥�̸� ���� �ٲ��ֱ� 
		 	if(CalendarService.weekDay(year, month, i) == 6 && i != CalendarService.lastDay(year, month)){
				td += "</tr><tr>";			
			}//end if
		}//end for
		 
		 if(CalendarService.weekDay(year, month, CalendarService.lastDay(year, month)) !=6){
			for(int i = CalendarService.weekDay(year, month, CalendarService.lastDay(year, month))+1; i < 7; i++){
				td += "<td></td>";	
			}//end for
		}//end if
	return td;
	}//dayOfWeek
	
	/**
	 * �޷��� ���۰� �������� ���� ����Ͽ� td�� �����ϴ� �޼���
	 * @param year
	 * @param month
	 * @return
	 */
	public String dayOfMonth(int year, int month) {
			String td="";
			
//		1���� ������ ����Ѵ�
			int first = CalendarService.weekDay(year, month, 1);
		
//		1���� ��µ� ��ġ ���� ������ ������ ��¥���� �־��ֱ����� �� �޳�¥�� �������� ����Ѵ�.
			int start = 0 ;
			start = month ==1? CalendarService.lastDay(year-1, 12)- first : CalendarService.lastDay(year, month-1)- first;

//		1���� ��µ� ��ġ�� ���߱� ���� 1���� ���ϸ�ŭ �ݺ��Ͽ� �����ǳ�¥�� ����Ѵ�.
			for(int i= 1; i<= first; i++){
				if(i==1){
//		�Ͽ���(������)�� �ٸ������� ���� �����ֱ� 
					td += "<td class = 'redbefore'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>";
				}else{
					td += "<td class = 'before'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>";
				}//end else
			}//end for
			
			return td;
	}//dayOfMonth
	
	public String searchCalendarMonth(CalendarMonthDomain cmDomain) {
		AdminCalendarDAO acDAO = new AdminCalendarDAO();
		String countMonth = acDAO.selectCalendarMonth(cmDomain);
		
		return countMonth;
	}//searchCalendarMonth
	
}//class