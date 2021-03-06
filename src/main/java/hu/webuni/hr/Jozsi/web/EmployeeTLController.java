package hu.webuni.hr.Jozsi.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.Jozsi.model.Employee;

@Controller
public class EmployeeTLController {
	
	
	private List<Employee> allEmployees = new ArrayList<>();

	{
		allEmployees.add(new Employee(1L,"KIss Gábor","osztályvezető",10000, LocalDateTime.of(2012, 1, 1, 8, 0,0)));
	}
	
	
	@GetMapping("employees")
	public String listEmployees(Map<String, Object> model) {
	 model.put("employees", allEmployees);
	 model.put("new emloyees", new Employee());
	 return "employees";
	}
	
	@GetMapping("employees/{id}")
	public String editEmployee(@PathVariable  long  id, Map<String, Object> model) {
	 Employee selectedEmployee = allEmployees.stream().filter(e->e.getEmployeeId().equals(id)).findFirst().get();
	 model.put("new emloyees", new Employee());
	 return "editEmployee";
	}
	
	
	@PostMapping
	public String addEmloyee(Employee employee) {
		allEmployees.add(employee);
		return "redirect:employee";
	
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee (@PathVariable  long  id) {
	 allEmployees.removeIf(e->e.getEmployeeId().equals(id));
	 return "redirect:/employees";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(Employee employee) {
		for (int i=0; i< allEmployees.size(); i++) {
			if (allEmployees.get(i).getEmployeeId().equals(employee.getEmployeeId())) {
			allEmployees.set(i, employee);
			break;
		    }
		}
	
		return "redirect:employee";

    }

}