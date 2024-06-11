package pro.sky.skyprospringmokito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/department") // нужно для того, чтобы не вводить эту надпись в каждом "@RequestMapping"
public class DepartmentController {
    private final DepartmentService companyService;

    public DepartmentController(DepartmentService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee employeeWithMinimumSalary(@PathVariable int id) {
        return companyService.employeeWithMinimumSalary(id);
    }
    @GetMapping(path = "/{id}/salary/max")
    public Employee employeeWithMaximumSalary(@PathVariable("id") int departmentId) {
        return companyService.employeeWithMaximumSalary(departmentId);
    }
    @GetMapping(path = "/{id}/employees")
    public List<Employee> printAllEmployee(@PathVariable("id") int departmentId) {
        return companyService.printAllEmployee(departmentId);
    }
    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> printAllEmployeeByDepartment() {
        return companyService.printAllEmployeeByDepartment();

    }
}
