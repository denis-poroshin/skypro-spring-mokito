package pro.sky.skyprospringmokito;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.*;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;
    @InjectMocks
    private DepartmentService departmentService;
    public final Employee employeeMinSalaryDepartmentOne = new Employee("Ivanov", "Ivan", 20_000, 1);
    public final Employee employeeMaxSalaryDepartmentOne = new Employee("Petrov", "Ignat", 25_000, 1);
    public final Employee employeeMinSalaryDepartmentTwo = new Employee("Smirnov", "Sergei", 30_000, 2);
    public final Employee employeeMaxSalaryDepartmentTwo = new Employee("Ivanov", "Sergei", 35_000, 2);
    public List<Employee> employeeSalary = new ArrayList<>(List.of(employeeMinSalaryDepartmentOne, employeeMaxSalaryDepartmentOne));
    public List<Employee> listOfEmployees = new ArrayList<>(List.of(employeeMinSalaryDepartmentOne, employeeMaxSalaryDepartmentOne,
            employeeMinSalaryDepartmentTwo, employeeMaxSalaryDepartmentTwo));

    public List<Employee> employeesListDepartmentOne = new ArrayList<>(List.of(employeeMinSalaryDepartmentOne, employeeMaxSalaryDepartmentOne));
    public List<Employee> employeesListDepartmentTwo = new ArrayList<>(List.of(employeeMinSalaryDepartmentTwo, employeeMaxSalaryDepartmentTwo));
    public Map<Integer, List<Employee>> employeeMap = new HashMap<>();


    @Test
    public void employeeWithMinimumSalaryTest(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(employeeSalary);
        assertEquals(employeeMinSalaryDepartmentOne, departmentService.employeeWithMinimumSalary(1));
    }
    @Test
    public void employeeWithMinimumSalaryTestNull(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(employeeSalary);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.employeeWithMinimumSalary(3));
    }
    @Test
    public void employeeWithMaximumSalaryTestNull(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(employeeSalary);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.employeeWithMaximumSalary(3));
    }
    @Test
    public void employeeWithMaximumSalaryTest(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(employeeSalary);
        assertEquals(employeeMaxSalaryDepartmentOne, departmentService.employeeWithMaximumSalary(1));
    }
    @Test
    public void printAllEmployee(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(listOfEmployees);
        Assertions.assertEquals(employeesListDepartmentOne, departmentService.printAllEmployee(1));

    }
    @Test
    public void printAllEmployeeByDepartmentTest(){
        employeeMap.put(1, employeesListDepartmentOne);
        employeeMap.put(2, employeesListDepartmentTwo);
        Mockito.when(employeeServiceMock.printAllEmployee()).thenReturn(listOfEmployees);
        Assertions.assertEquals(departmentService.printAllEmployeeByDepartment(), employeeMap);

    }


}
