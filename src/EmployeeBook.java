import java.util.*;

public class EmployeeBook {
    private Map<String, Employee> employees; // <FIO, Employee>
    private Map<Integer, String> mapIdFIO; // <keyId, FIO>

    public EmployeeBook() {
        employees = new HashMap<>();
        mapIdFIO = new HashMap<>();
    }

    public String getEmployeeList() {
        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees.values()) {
            sb.append(employee + "\n");
        }

        return sb.toString();
    }

    public String keyGenByFIO(String surname, String name, String secondName) {
        return surname.toUpperCase() + name.toUpperCase() + secondName.toUpperCase();
    }

    public void addNewEmployee(String surname, String name, String secondName, int department, float salary) {
        Employee newEmployee = new Employee(surname, name, secondName, department, salary);
        /*boolean success = false;
        if (!success) {
            throw new RuntimeException("Нет места в массиве employees для нового сотрудника");
        }
        */

        String keyFIO = keyGenByFIO(surname, name, secondName);
        employees.put(keyFIO, newEmployee);
        mapIdFIO.put(newEmployee.getId(), keyFIO);
    }

    // Удаление элемента массива (сотрудника) по ФИО
    public void deleteEmployee(String surname, String name, String secondName) {
        if (employees.remove(keyGenByFIO(surname, name, secondName)) != null) {
            System.out.println("Успешно удалены записи " + surname + " " + name + " " + secondName);
        } else {
            System.out.println("Не найден сотрудник " + surname + " " + name + " " + secondName);
        }
    }

    // Удаление элемента массива (сотрудника) по id
    public void deleteEmployee(int id) {
        String keyFIO = mapIdFIO.get(id);
        mapIdFIO.remove(id);
        Employee deletedEmployee = employees.remove(keyFIO);
        if (deletedEmployee != null) {
            System.out.println("Успешно удалены записи " + deletedEmployee);
        } else {
            System.out.println("Не удалось удалить запись по id = " + id);
        }

    }

    public List<Employee> getListByDep(int dep) { // получение списка List сотрудников департамента dep
        List <Employee> resultList = new ArrayList<>();

        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == dep) {
                resultList.add(employee);
            }
        }
        return resultList;
    }

    // 6. Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
    public String getFIOByDep() {
        Map<Integer, StringBuilder> mapResults = new HashMap<>(); // хранит строки ФИО сотрудников определенного отдела по ключу "номер отдела"

        for (Employee employee : employees.values()) {
            int keyDepartment = employee.getDepartment();
            if (!mapResults.containsKey(keyDepartment)) {
                mapResults.put(keyDepartment,
                        new StringBuilder(employee.getFullName()));
            }else{
                mapResults.put(keyDepartment,
                        mapResults.get(keyDepartment).append(", " + employee.getFullName()));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer key : mapResults.keySet()) {
            sb.append("Отдел №" + key + ": " + mapResults.get(key) + "\n");
        }

        return sb.toString();
    }

    // Сумма затрат на зарплаты в месяц

    public float calcSumSalary() {
        float sum = 0f;
        for (Employee employee : employees.values()) {
                sum += employee.getSalary();
        }
        return sum;
    }

    private Employee findMinSalaryForList(List <Employee> listEmployees) {
        Employee resultEmployee = listEmployees.get(0);
        float min = listEmployees.get(0).getSalary();

        for (Employee employee : listEmployees) {
            if (min > employee.getSalary()) {
                min = employee.getSalary();
                resultEmployee = employee;
            }
        }

        return resultEmployee;
    }

    private Employee findMaxSalaryForList(List <Employee> listEmployees) {
        Employee resultEmployee = listEmployees.get(0);
        float max = listEmployees.get(0).getSalary();

        for (Employee employee : listEmployees) {
            if (max < employee.getSalary()) {
                max = employee.getSalary();
                resultEmployee = employee;
            }
        }

        return resultEmployee;
    }

    public Employee findMinSalary() {
        List <Employee> list = new ArrayList<>(employees.values());
        return findMinSalaryForList(list);
    }

    public Employee findMaxSalary() {
        List <Employee> list = new ArrayList<>(employees.values());
        return findMaxSalaryForList(list);
    }

    public float calcAverageSalary() {
        int n = employees.size();
        if (n == 0) {
            throw new RuntimeException("Нет сотрудников, Возникает деление на 0");
        }
        return calcSumSalary() / n;

    }

    public String getFullNames() {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees.values()) {
            sb.append(employee.getFullName() + "\n");
        }
        return sb.toString();
    }

    // повышенная сложность
    public void indexSalaries(float percent) {
        for (Employee employee : employees.values()) {
            employee.indexSalary(percent);
        }
    }

    // Поиск сотрудника с минимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public Employee findMinSalaryForDepartment(int dep) {
        List<Employee> list = getListByDep(dep);
        if(list.size() == 0){
            return null;
        }else {
            return findMinSalaryForList(list);
        }
    }

    // Поиск сотрудника с максимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public Employee findMaxSalaryForDepartment(int dep) {
        List<Employee> list = getListByDep(dep);
        if(list.size() == 0){
            return null;
        }else {
            return findMaxSalaryForList(list);
        }
    }

    // Сумма затрат на ЗП по отделу
    public float calcSumSalaryForDepartment(int dep) {
        float sum = 0f;
        List<Employee> list = getListByDep(dep);

        for (Employee employee : list) {
            sum += employee.getSalary();
        }
        return sum;
    }

    // Сумма средней ЗП по отделу. Возвращает Ср сумму ЗП или -1, если в отделе нет сотрудников
    public float calcAverageSalaryForDepartment(int dep) {
        List<Employee> list = getListByDep(dep);

        if (list.size() > 0) {
            return calcSumSalaryForDepartment(dep) / list.size();
        } else {
            return (-1);
        }
    }

    // Проиндесировать ЗП сотрудников на % = percent отдела dep
    public void indexSalaries(float percent, int dep) {
        List<Employee> list = getListByDep(dep);
        for (Employee employee : list) {
            employee.indexSalary(percent);
        }
    }

    // Получить данные по всем сотрудникам отдела dep, не включая номер отдела
    public String getEmployeeListForDepartment(int dep) {
        StringBuilder sb = new StringBuilder();

        List<Employee> list = getListByDep(dep);
        for (Employee employee : list) {
            sb.append(employee.getDataWithoutDepartment() + "\n");
        }
        return sb.toString();
    }

    public String getListSalaryLess(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees.values()) {
            if (employee.getSalary() < salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }

    public String getListSalaryOver(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees.values()) {
            if (employee.getSalary() >= salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }

    // Поиск индекса элемента в массиве с заданым ФИО (поиск сотрудника по ФИО)
    public Employee getEmployeeByFIO(String surname, String name, String secondName) {
        return employees.get(keyGenByFIO(surname, name, secondName));
    }

    // Изменение ЗП у сотрудника по ФИО
    public void changeSalaryByFIO(String surname, String name, String secondName, float newSalary) {
        if (newSalary < 0) {
            throw new RuntimeException("Передана ЗП меньше 0");
        }
        Employee employee = getEmployeeByFIO(surname, name, secondName);
        if(employee != null){
            employee.setSalary(newSalary);
            System.out.println("ЗП " + surname + " " + name + " " + secondName + " успешно изменена");
        }else {
            System.out.println("Не удалось найти сотрудника с заданным ФИО: " + surname + " " + name + " " + secondName);
        }
    }

    public void changeDepartmentByFIO(String surname, String name, String secondName, int newDepartment) {
        if (newDepartment < 1 || newDepartment > 5) {
            throw new RuntimeException("Передан отдел за пределами допустимого (допустимо от 1 до 5)");
        }
        Employee employee = getEmployeeByFIO(surname, name, secondName);
        if (employee != null) {
            employee.setDepartment(newDepartment);
            System.out.println("Отдел для сотрудника " + surname + " " + name + " " + secondName + " успешно изменен");
        } else {
            System.out.println("Не удалось найти сотрудника с заданным ФИО: " + surname + " " + name + " " + secondName);
        }
    }
}
