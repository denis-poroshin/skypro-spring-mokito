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
    @BeforeEach
    public void setUp(){
         employee = new Employee("Ivanov", "Ivan", 20_000, 1);
        employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1);


    }

    @Test
    public void checkingTheEmployeesPresenceInTheDatabase(){
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivanov", "Ivan", 20_000, 1));
    }
    @Test
    public void checkingForErrorsWhenDeletingAnEmployee(){
        String name = "Ivanbbbbb";
        String familName = "Ivanov";
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.dismissalEmployee(familName, name));
    }
    @Test
    public void searchEmployeeTest(){
        String name = "Ivan";
        String familName = "Ivanov";
        assertEquals(employee, employeeService.searchEmployee(familName,name));

    }
    @Test
    public void checkingForErrorsWhenSearchAnEmployee(){
        String name = "Ivanbbbbb";
        String familName = "Ivanov";
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.dismissalEmployee(familName, name));

    }
    @Test
    public void dismissalEmployeeTest(){
        String name = "Ivan";
        String familName = "Ivanov";
        assertEquals(employeeService.dismissalEmployee(familName, name), employee);
    }
    @Test
    public void dismissalEmployeeExceptionsTest(){
        String name = "Ivannn";
        String familName = "Ivanovvv";
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.dismissalEmployee(familName, name));
    }

    @Test
    public void checkKyeTestForMethodDismissalEmployee(){
        String name = "IVAN1";
        String familName = "IVANoV1";

        assertThrows(EmployeeuserNotEnterFirstOrLastNameInformationException.class,
                () -> employeeService.dismissalEmployee(familName, name));
    }
    @Test
    public void checkKyeTestForMethodSearchEmployee(){
        String name = "IVAN1";
        String familName = "IVANoV1";

        assertThrows(EmployeeuserNotEnterFirstOrLastNameInformationException.class,
                () -> employeeService.searchEmployee(familName, name));
    }
    @Test
    public void checkKyeTestForMethodAddEmployee(){
        String name = "IVAN1";
        String familName = "IVANoV1";
        int salary = 25_000;
        int department = 1;

        assertThrows(EmployeeuserNotEnterFirstOrLastNameInformationException.class,
                () -> employeeService.addEmployee(familName, name, salary, department));
    }



}
