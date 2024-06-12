package pro.sky.skyprospringmokito;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    public Employee employeeWithMinimumSalary(int department){
        return employeeService.printAllEmployee().
                stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparingInt(empl -> empl.getSalary())).
                orElseThrow(() -> new EmployeeNotFoundException("Сотрудник отсутствует в базе данных"));


    }
    public Employee employeeWithMaximumSalary(int department){
        return employeeService.printAllEmployee().
                stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparingInt(empl -> empl.getSalary())).
                orElseThrow(() -> new EmployeeNotFoundException("Сотрудник отсутствует в базе данных"));


    }
    public List<Employee> printAllEmployee(int department){

        return employeeService.printAllEmployee()
                .stream()
                .filter(emp -> emp.getDepartment() == department)
                .collect(Collectors.toList());

    }
    public Map<Integer, List<Employee>> printAllEmployeeByDepartment() {
        return employeeService.printAllEmployee()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        () -> new TreeMap<Integer, List<Employee>>(), Collectors.toList()));

    }
}
