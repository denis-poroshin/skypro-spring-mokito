package pro.sky.skyprospringmokito;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeService {

    private final Map<String, Employee> employeeMap;
    private final int maxEmplyee = 5;


    public EmployeeService() {
        employeeMap = new HashMap<>();
    }

    public Employee addEmployee(String familName, String name, int salary, int department) {
        checkKye(familName, name); //првоерка на то, что имя и фамилия состоят только из букв

        String key = keyGeneration(familName, name);
        if (employeeMap.containsKey(keyGeneration(familName, name))) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже работает в компании");
        } else if (employeeMap.size() > maxEmplyee) {
            throw new EmployeeStorageIsFullException("Мест для новых сотрудников нет");
        }
        Employee newEmployee = new Employee(familName, name, salary, department);
        employeeMap.put(key, newEmployee);
        return employeeMap.get(key);

    }

    public Employee dismissalEmployee(String familName, String name) {
        checkKye(familName, name); //првоерка на то, что имя и фамилия состоят только из букв
        String key = keyGeneration(familName, name);
        if (employeeMap.containsKey(key)) {
            employeeMap.remove(key);
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник отсутствует в базе данных, увольнение невозможно");
    }

    public Employee searchEmployee(String familName, String name) {
        checkKye(familName, name); //првоерка на то, что имя и фамилия состоят только из букв
        String key = keyGeneration(familName, name);
        if (employeeMap.containsKey(key)) {
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник отсутствует в базе данных");
    }


    public String keyGeneration(String familName, String name) {
        return familName + name;
    }

    public void checkKye(String familName, String name) {
        if (!(StringUtils.isAlpha(familName) && StringUtils.isAlpha(name))) { //Являются только буквами
            throw new EmployeeuserNotEnterFirstOrLastNameInformationException();
        }

    }

    public Collection<Employee> printAllEmployee() {
        return Collections.unmodifiableCollection(employeeMap.values()); // создаст неизменяемую копию мапы


    }
}
