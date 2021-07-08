package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.sist.service.AdminHomeService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminHomeController {
	
	@RequestMapping(value="/adminHome/admin_home_form.do",method = {GET,POST} )
	public String homeFrm( Model model ) {
		
		AdminHomeService ahs = new AdminHomeService();
		model.addAttribute("todayBooking",ahs.searchTodayBooking());
		model.addAttribute("todaySales",ahs.searchTodaySales());
		model.addAttribute("todayGuest",ahs.searchTodayGuest());
		model.addAttribute("tomorrowGuest",ahs.searchTomorrowGuest());
		model.addAttribute("thisMonthGuest",ahs.searchThisMonthGuest());
		model.addAttribute("thisMonthSales",ahs.searchThisMonthSales());
		model.addAttribute("lastMonthGuest",ahs.searchLastMonthGuest());
		model.addAttribute("lastMonthSales",ahs.searchLastMonthSales());
		
		return "adminHome/admin_home_form";
	}//homeFrm

}
