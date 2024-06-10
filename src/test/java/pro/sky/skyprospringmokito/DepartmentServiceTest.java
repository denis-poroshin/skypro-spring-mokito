package pro.sky.skyprospringmokito;

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
    //private final Employee employee = new Employee("Ivanov", "Ivan", 20_000, 1);

    @Mock
    private EmployeeService employeeServiceMock;
    @InjectMocks
    private DepartmentService departmentService;
    public final Employee employeeMinSalary = new Employee("Ivanov", "Ivan", 20_000, 1);
    public final Employee employeeMaxSalary = new Employee("Petrov", "Ignat", 25_000, 1);
    public final Employee employee = new Employee("Smirnov", "Sergei", 30_000, 2);
    public List<Employee> EMPLOYEE = new ArrayList<>(List.of(employeeMinSalary, employeeMaxSalary, employee));
    public List<Employee> employeesList = new ArrayList<>(List.of(employeeMinSalary, employee));
    public Map<Integer, List<Employee>> employeeMap = new HashMap<>();


    @Test
    public void employeeWithMinimumSalaryTest(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(EMPLOYEE);
        assertEquals(employeeMinSalary, departmentService.employeeWithMinimumSalary(1));
    }
    @Test
    public void employeeWithMinimumSalaryTestNull(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(EMPLOYEE);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.employeeWithMinimumSalary(3));
    }
    @Test
    public void employeeWithMaximumSalaryTestNull(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(EMPLOYEE);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.employeeWithMaximumSalary(3));
    }
    @Test
    public void employeeWithMaximumSalaryTest(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(EMPLOYEE);
        assertEquals(employeeMaxSalary, departmentService.employeeWithMaximumSalary(1));
    }
    @Test
    public void printAllEmployee(){
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(EMPLOYEE);
        assertEquals(employeeMaxSalary, departmentService.employeeWithMaximumSalary(1));
    }
    @Test
    public void printAllEmployeeByDepartmentTest(){ //Не совем понимаю как правильно разделить сотрудников по отделам с помощью List
        employeeMap.put(1, employeesList);
        employeeMap.put(2, employeesList);
        Mockito.when(employeeServiceMock.printAllEmployee()).
                thenReturn(employeesList);
        employeeMap.put(employeeMinSalary.getDepartment(), employeesList);
        Assertions.assertEquals(departmentService.printAllEmployeeByDepartment(), employeeMap);

    }


}
