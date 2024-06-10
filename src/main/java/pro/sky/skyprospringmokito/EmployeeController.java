package pro.sky.skyprospringmokito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee") // нужно для того, чтобы не вводить эту надпись в каждом "@RequestMapping"
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/add")
    public Employee addEmployee(@RequestParam("lastName") String lastName,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("salary") int salary,
                                @RequestParam("department") int department) {
        return employeeService.addEmployee(lastName, firstName, salary, department);
    }

    @RequestMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName) {
        return employeeService.dismissalEmployee(lastName, firstName);
    }

    @RequestMapping(path = "/find")
    public Employee findEmployee(@RequestParam("lastName") String lastName,
                                 @RequestParam("firstName") String firstName) {
        return employeeService.searchEmployee(lastName, firstName);
    }

    @GetMapping
    public Collection<Employee> printEmployee() {
        return employeeService.printAllEmployee();
    }
}
