package pro.sky.skyprospringmokito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class EmployeeServiceTest {


    EmployeeService employeeService = new EmployeeService();

    Employee employee;
//    private Map<String, Employee> employeeMap = Map.of("IvanovIvan", employee);
    @BeforeEach
    public void setUp(){
         employee = new Employee("Ivanov", "Ivan", 20_000, 1);
        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);

    }




    @Test
    public void keyGenerationTest(){
        String actualKey = "IvanIvanov";
        assertEquals(actualKey, employeeService.keyGeneration("Ivan", "Ivanov"));
    }
    @Test
    public void checkingParametersWhenAddingAnEmployee(){
        String actualName = "Ivan";
        String actualFamilName = "Ivanov";
        int actualSalary = 20_000;
        int actualDepartment = 1;


        assertEquals(actualName, employee.getName());
        assertEquals(actualFamilName, employee.getFamilName());
        assertEquals(actualSalary, employee.getSalary());
        assertEquals(actualDepartment, employee.getDepartment());
    }
    @Test
    public void checkingTheEmployeesPresenceInTheDatabase(){
//        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);


        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1));
    }
    @Test
    public void checkingForErrorsWhenDeletingAnEmployee(){
        String actualName = "Ivanbbbbb";
        String actualFamilName = "Ivanov";
//        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.dismissalEmployee(actualFamilName, actualName));
    }
    @Test
    public void searchEmployeeTest(){
        String actualName = "Ivan";
        String actualFamilName = "Ivanov";
//        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);
        assertEquals(employee, employeeService.searchEmployee(actualFamilName,actualName));

    }
    @Test
    public void checkingForErrorsWhenSearchAnEmployee(){
        String actualName = "Ivanbbbbb";
        String actualFamilName = "Ivanov";
//        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.dismissalEmployee(actualFamilName, actualName));

    }
    @Test
    public void dismissalEmployeeTest(){
        String actualName = "Ivan";
        String actualFamilName = "Ivanov";
//        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);
        assertNull(employeeService.dismissalEmployee(actualFamilName, actualName));
    }
    @Test
    public void dismissalEmployeeTest2(){
        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);
//        Map<String, Employee> employeeMap = Map.of("IvanovIvan", employee);
        assertEquals(employee, employeeService.printAllEmployee()); // Как преобразовать список в массиов? Пока не понятно...
    }



    @Test
    public void checkKyeTest(){
        String expectedName = "IVAN1";
        String expectedFamilName = "IVANoV1";

        assertThrows(EmployeeuserNotEnterFirstOrLastNameInformationException.class,
                () -> employeeService.checkKye(expectedFamilName, expectedName));
    }


}
