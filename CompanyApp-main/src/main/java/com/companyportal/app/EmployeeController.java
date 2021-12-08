package com.companyportal.app;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.companyportal.app.entity.Employee;
import com.companyportal.app.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayRegistrationForm(Model model) {
		Employee employee = new Employee();

		model.addAttribute("employee", employee);
		return "employeeform";

//return new ModelAndView("employeeform", "employee", employee);

	}

	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public String saveOrUpdateEmployee(@ModelAttribute Employee employee) {
		employeeService.saveOrUpdateEmployee(employee);

		return "redirect:/employeelist";
	}

	@RequestMapping(value = "/employeelist", method = RequestMethod.GET)
	public String getEmployeesData(Model model) {
		List<Employee> employeeList = employeeService.getEmployeesData();

		model.addAttribute("employeeList", employeeList);
		return "employeelist";
	}

	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.GET)
	public String deleteEmployeeData(@PathVariable("employeeId") int employeeId) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employeeService.deleteEmployee(employee);
		return "redirect:/employeelist";
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
	public String updateEmployeeData(@RequestParam("employeeId") int employeeId, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
		return "employeeform";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {
		return "search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchEmployee(@RequestParam("employeeName") String employeeName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.addObject("emplist", employeeService.searchEmployeebyName(employeeName));
		System.out.println(employeeService.searchEmployeebyName(employeeName));
		return mv;
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		Employee employee = new Employee();

		model.addAttribute("employee", employee);
		return "employeeform";

	}

//  save employee request

	@PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Employee savaEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdateEmployee(employee);
		return employee;
	}

// delete employee request

	@DeleteMapping(value = "/employees/{employeeId}")
	@ResponseBody
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employeeService.deleteEmployee(employee);
		return "Deleted Seccessfully";

		// return "redirect:/employeelist";

	}

// get all details of employee request

	@GetMapping(value = "/employees")
	@ResponseBody
	public List<Employee> getallEmployees() {
		List<Employee> employeeList = employeeService.getEmployeesData();
		return employeeList;
	}

}