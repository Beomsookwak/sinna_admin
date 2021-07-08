package kr.co.sist.service;

import kr.co.sist.dao.AdminCalendarDAO;
import kr.co.sist.domain.CalendarAboutDomain;
import kr.co.sist.domain.CalendarMonthDomain;

public class CalendarService {
	
	/**
	 * 년도를 넘겨받아 윤년/ 평년을 판단해 윤년이면 true, 평년이면 false를 리턴하는 메서드
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 ==0) && (year % 100 !=0) ||(year % 400 ==0);
	}//isLeapYear
	
	/**
	 * 년, 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메서드
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
	 * 년, 월, 일을 념겨받아 1년 1월 1일부터 지나온 날짜의 합계를 리턴하는 메서드
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
	 * 년, 월, 일을 넘겨받아 요일을 숫자로 리턴하는 메서드, 일요일(0),월요일(1)....토요일(6)
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}//weekDay
	
	/**
	 * 요일 구분과 줄 바꿈으로 만들어진 td를 리턴하는 메서드
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
		  //요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기 
			CalendarAboutDomain caDomainDeluxe = new CalendarAboutDomain(yearMonth+String.format("%02d", i),deluxe);
			CalendarAboutDomain caDomainSuite = new CalendarAboutDomain(yearMonth+String.format("%02d", i),suite);

			 switch(CalendarService.weekDay(year, month, i)){
				case 0 : //일요일
					td += "<td class ='sun'>" +i+"<br/>";
					break;
				case 6 : //토요일
					td += "<td class ='sat'>" +i+"<br/>";
					break;
				default : //평일
					td += "<td class ='etc'>" +i+"<br/>";
					break;
			}//end switch
	 		if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("Y") ) {
	 			td += "<span class='cal_result'>디럭스 : <img src='"+ok+"'/></span><br/>";
	 		}else if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("C") ){
	 			td += "<span class='cal_result'>디럭스 : <img src='"+wait+"'/></span><br/>";
	 		}else if( acDAO.selectCalendarAbout( caDomainDeluxe ).equals("N") ){
	 			td +=  "<span class='cal_result'>디럭스 : <img src='"+cancle+"'/></span><br/>";
	 		}else{
	 			td += "<span class='cal_result'>디럭스 : </span><br/>";
	 		}//end if
			
			if( acDAO.selectCalendarAbout( caDomainSuite ).equals("Y") ) {
				td += "<span class='cal_result'>스위트 : <img src='"+ok+"'/></span><br/>";
			}else if( acDAO.selectCalendarAbout( caDomainSuite ).equals("C") ){
				td += "<span class='cal_result'>스위트 : <img src='"+wait+"'/></span><br/>";
			}else if( acDAO.selectCalendarAbout( caDomainSuite ).equals("N") ){
				td +=  "<span class='cal_result'>스위트 : <img src='"+cancle+"'/></span><br/>";
			}else{
				td += "<span class='cal_result'>스위트 : </span><br/>";
			}//end if
		 	
		 	// 출력한 날짜(i)가 토요일이고 그달의 마지막 날짜이면 줄을 바꿔주기 
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
	 * 달력의 시작과 이전달의 끝을 계산하여 td를 리턴하는 메서드
	 * @param year
	 * @param month
	 * @return
	 */
	public String dayOfMonth(int year, int month) {
			String td="";
			
//		1일의 요일을 계산한다
			int first = CalendarService.weekDay(year, month, 1);
		
//		1일이 출력될 위치 전에 전달의 마지막 날짜들을 넣어주기위해 전 달날짜의 시작일을 계산한다.
			int start = 0 ;
			start = month ==1? CalendarService.lastDay(year-1, 12)- first : CalendarService.lastDay(year, month-1)- first;

//		1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하여 전달의날짜를 출력한다.
			for(int i= 1; i<= first; i++){
				if(i==1){
//		일요일(빨간색)과 다른날들의 색을 구별주기 
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