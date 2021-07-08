package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.domain.CalendarMonthDomain;
import kr.co.sist.service.AdminBookingService;
import kr.co.sist.service.CalendarService;
import kr.co.sist.vo.BookingModifyVO;


@Controller
public class AdminBookingController {
	
	/**
	 * Main Form
	 * @return
	 */
	@RequestMapping(value="/adminBooking/booking_main_form.do",method = GET )
	public String bookingMainFrm(Model model,
			 HttpServletRequest requestYear, HttpServletRequest requestMonth) {
		
		// 컴퓨터 시스템의 년, 월 받아오기
		Date date = new Date();
		int year = date.getYear() +1900;
		int month = date.getMonth() +1;
		//	오류사항 걸러주기	
		try{
			if( requestYear.getParameter("year") != null && requestMonth.getParameter("month") != null ) {
				year = Integer.parseInt(requestYear.getParameter("year"));
				month = Integer.parseInt(requestMonth.getParameter("month"));
				
				if(month>=13){
					year++;
					month =1;
				}else if(month <=0){
					year--;
					month =12;
				}//end else
		}//end if
		}catch(Exception e){
			e.printStackTrace();
		}//end catch
		
		CalendarService cs = new CalendarService();
		String day = year+"-"+String.format("%02d", month) +"-";
		
		CalendarMonthDomain cmDomainY = new CalendarMonthDomain("Y",day);
		CalendarMonthDomain cmDomainC = new CalendarMonthDomain("C",day);
		CalendarMonthDomain cmDomainN = new CalendarMonthDomain("N",day);
		
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		model.addAttribute("dayStatusResult", cs.dayOfWeek(year, month) );
		model.addAttribute("monthStatusResult", cs.dayOfMonth(year, month) );
		model.addAttribute("Y", cs.searchCalendarMonth(cmDomainY) );
		model.addAttribute("C", cs.searchCalendarMonth(cmDomainC) );
		model.addAttribute("N", cs.searchCalendarMonth(cmDomainN) );
		
		return "adminBooking/booking_main_form";
	}//bookingMainFrm
	
	/**
	 * 예약정보 전체 검색
	 * @param text_search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminBooking/use_search.do",method = {GET,POST} )
	public String bookingSearchProcess( @RequestParam String text_search, Model model ) {
		AdminBookingService abs = new AdminBookingService();
		
		model.addAttribute("bookingList",abs.searchMainBooking(text_search).toJSONString());
		
		return "adminBooking/search_json";
	}//bookingSearchProcess
	
	/**
	 * 예약상세 정보
	 * @param booking_no 선택된 예약 번호
	 * @param thisBooking 예약번호를 가져오기 위한 배열 
	 * @param btnNo 예약번호를 가져오기 위한 배열
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminBooking/booking_one.do",method = {GET,POST} )
	public String bookingOneFrm( String booking_no, String[] thisBooking, String[] btnNo, Model model ) {
		
		booking_no =thisBooking[Integer.parseInt( btnNo[0] )]; //해당하는 예약 번호 가져오기
		
		AdminBookingService abs = new AdminBookingService();
		model.addAttribute("bookingInfo",abs.searchOneBooking(booking_no));
		
		
		return "adminBooking/booking_one_form";
	}//bookingOneFrm
	
	/**
	 * 예약 수정을 위한 method
	 * @param bmVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminBooking/booking_modify.do",method = {GET,POST} )
	public String bookingModifyProcess( BookingModifyVO bmVO, Model model ) {
		
		AdminBookingService abs = new AdminBookingService();
		model.addAttribute("rowCnt",abs.modifyBooking(bmVO));
		
		return "adminBooking/booking_modify_result";
	}//bookingModefyProcess

}
