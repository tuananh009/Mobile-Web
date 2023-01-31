package bkap.happyshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bkap.happyshop.dao.ReportDAO;

@Controller
public class InventoryReportController {
	@Autowired
	ReportDAO reportDAO;

	// Tồn Kho theo loại
	@RequestMapping("/admin/inventory/index")
	public String index(Model m) {
		m.addAttribute("data", reportDAO.inventory());
		return "admin/report/inventory";
	}
	
	//Doanh thu theo loại hầng
	@RequestMapping("/admin/report/revenue-by-category")
	public String revenueByCategory(Model m) {
		m.addAttribute("data", reportDAO.revenueByCategory());
		return "admin/report/revenue-by-category";
	}

	// Doanh thu theo khách hàng
	@RequestMapping("/admin/report/revenue-by-customer")
	public String revenueByCustomer(Model m) {
		m.addAttribute("data", reportDAO.revenueByCustomer());
		return "admin/report/revenue-by-customer";
	}
	
	//Doanh thu theo năm
	@RequestMapping("/admin/report/revenue-by-year")
	public String revenueByYear(Model m) {
		m.addAttribute("data", reportDAO.revenueByYear());
		return "admin/report/revenue-by-year";
	}
	//Doanh thu theo quý
	@RequestMapping("/admin/report/revenue-by-quarter")
	public String revenueByQuarter(Model m) {
		m.addAttribute("data", reportDAO.revenueByQuarter());
		return "admin/report/revenue-by-quarter";
	}
	
	//Doanh thu theo tháng
	@RequestMapping("admin/report/revenue-by-month")
	public String revenueByMonth(Model m) {
		m.addAttribute("data", reportDAO.revenueByMonth());
		return "admin/report/revenue-by-month";
	}
}
