public class Main {

    public static Employee[] employees;

    public static void main(String[] args) {
        employees = new Employee[10];
        employees[0] = new Employee("Иванов", "Иван", "Иванович", 2, 50000f);
        employees[1] = new Employee("Петров", "Петр", "Петрович", 1, 40000f);
        employees[2] = new Employee("Васильев", "Василий", "Васильевич", 1, 70000f);
        System.out.println(getEmployeeList());
        System.out.println("Сумма затрат на ЗП в месяц = " + calcSumSalary());
        System.out.println("Сотрудник с минимальной ЗП: " + findMinSalary());
        System.out.println("Сотрудник с максимальной ЗП: " + findMaxSalary());
        System.out.println("Средняя ЗП = " + calcAverageSalary());
        System.out.println("ФИО сотрудников:\n" + getFullNames());
        System.out.println("Увеличим ЗП на 10%");
        indexSalaries(10);
        System.out.println(getEmployeeList());
        System.out.println("Уменьшим ЗП на 5%");
        indexSalaries(-5);
        System.out.println(getEmployeeList());

        int dep = 1;
        Employee em = findMinSalaryForDepartment(dep);
        if (em != null){
            System.out.println("Сотрудник с минимальной ЗП по отделу " + dep + ": " + em);
        } else{
            System.out.println("В отделе " + dep + " нет сотрудников");
        }

        System.out.println("Сотрудник с максимальной ЗП по отделу 1 = " + findMaxSalaryForDepartment(1));
        System.out.println("Сумма затрат на ЗП по отделу " + dep + " = " + calcSumSalaryForDepartment(dep));

        //dep = 2;
        if (calcAverageSalaryForDepartment(dep) == -1) {
            System.out.println("Нет сотрудников в отделе " + dep);
        } else {
            System.out.println("Сумма средней ЗП по отделу " + dep + " = " + calcAverageSalaryForDepartment(dep));
        }

        System.out.println("До индексации");
        System.out.println(getEmployeeList());
        indexSalaries(50, 1);
        System.out.println("После индексации");
        System.out.println(getEmployeeList());
        System.out.println("\nДанные по отделу " + dep);
        System.out.println(getEmployeeListForDepartment(dep));

        System.out.println("Сотрудники с ЗП меньше 55000");
        System.out.println(getListSalaryLess(55000));
        System.out.println("Сотрудники с ЗП больше либо равно 55000");
        System.out.println(getListSalaryOver(55000));

        // -----------------------------------------------------
        System.out.println(" ============= EmployeeBook =============");
        EmployeeBook book = new EmployeeBook();
        book.addNewEmployee("Иванов", "Иван", "Иванович", 3, 50000f);
        book.addNewEmployee("Пончиков", "Иван", "Иванович", 3, 50000f);
        book.addNewEmployee("Сидоров", "Олег", "Иванович", 1, 50000f);
        book.addNewEmployee("Михайлова", "Ольга", "Ивановна", 2, 50000f);
        book.addNewEmployee("Иванов", "Иван", "Иванович", 5, 50000f);
        book.addNewEmployee("Еленова", "Елена", "Ивановна", 5, 75000f);
        book.addNewEmployee("Евгеньева", "Евгения", "Петровна", 5, 85000f);
        book.addNewEmployee("Крапивина", "Юлия", "Михайловна", 5, 30005f);

        System.out.println(book.getFIOByDep());                                // Вывод ФИО сотрудников по отделам
        System.out.println(book.getEmployeeList());
        book.deleteEmployee("Пончиков", "Иван", "Иванович"); // удаление сотрудника по ФИО
        book.deleteEmployee("Пончиков111", "Иван", "Иванович"); // удаление сотрудника по ФИО
        book.deleteEmployee(5);                                                   // удаление сотрудника по id
        book.deleteEmployee(255);                                                   // удаление сотрудника по id
        System.out.println(book.getEmployeeList());

        System.out.println(book.getFIOByDep());                                // Вывод ФИО сотрудников по отделам

        System.out.println("\nСумма затрат на ЗП сотрудников = " + book.calcSumSalary());
        System.out.println("Минимальная ЗП составляет = " + book.findMinSalary().getSalary());
        System.out.println("Максимальная ЗП составляет = " + book.findMaxSalary().getSalary());
        System.out.println("Средняя ЗП составляет = " + book.calcAverageSalary());
        System.out.println("\n" + book.getFullNames());
        book.indexSalaries(50);
        System.out.println("Увеличили ЗП на 50%. Выведем всех сотрудников");
        System.out.println(book.getEmployeeList());

        System.out.println(book.getFIOByDep());
        System.out.println("Минимальная ЗП в отделе 5 = " + book.findMinSalaryForDepartment(5));
        System.out.println("Максимальная ЗП в отделе 5 = " + book.findMaxSalaryForDepartment(5));
        System.out.println("book.calcSumSalaryForDepartment(5) = " + book.calcSumSalaryForDepartment(5));
        System.out.println("book.calcAverageSalary() = " + book.calcAverageSalaryForDepartment(5));
        book.indexSalaries(100, 5);
        System.out.println("\nВ 5-м отделе индексация 100%");
        System.out.println(book.getEmployeeList());
        System.out.println("book.getEmployeeListForDepartment(5): \n" + book.getEmployeeListForDepartment(5));
        System.out.println("book.getListSalaryLess(150000): \n" + book.getListSalaryLess(150000));
        System.out.println("book.getListSalaryOver(150000): \n" + book.getListSalaryOver(150000));
        System.out.println("Поиск сотрудника по ФИО: " + book.getEmployeeByFIO("Крапивина", "Юлия", "Михайловна"));
        book.changeSalaryByFIO("Крапивина", "Юлия", "Михайловна", 95000);
        System.out.println("Поиск сотрудника по ФИО: " + book.getEmployeeByFIO("Крапивина", "Юлия", "Михайловна"));
        book.changeDepartmentByFIO("Крапивина", "Юлия", "Михайловна", 4);
        System.out.println("Поиск сотрудника по ФИО: " + book.getEmployeeByFIO("Крапивина", "Юлия", "Михайловна"));



        book.changeSalaryByFIO("Еленова", "Елена", "Ивановна111", 70000);
        book.changeSalaryByFIO("Евгеньева", "Евгения", "Петровна", 70000);
        book.changeSalaryByFIO("Крапивина", "Юлия", "Михайловна", 85000);
        book.changeDepartmentByFIO("Крапивина", "Юлия", "Михайловна", 1);
        System.out.println("\n" + book.getEmployeeList());

    }

    public static String getEmployeeList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sb.append(employees[i] + "\n");
            }
        }
        return sb.toString();
    }

    // Сумма затрат на зарплаты в месяц
    public static float calcSumSalary() {
        float sum = 0f;
        for (Employee employee : employees) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    public static Employee findMinSalary() {
        float min = employees[0].getSalary();
        int idx = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && min > employees[i].getSalary()) {
                min = employees[i].getSalary();
                idx = i;
            }
        }
        return employees[idx];
    }

    public static Employee findMaxSalary() {
        float max = employees[0].getSalary();
        int idx = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && max < employees[i].getSalary()) {
                max = employees[i].getSalary();
                idx = i;
            }
        }
        return employees[idx];
    }

    public static float calcAverageSalary() {
        int n = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                n++; // кол-во сотрудников реально присутствующих в массиве (заполнены данные на них) != null
            }
        }
        return calcSumSalary() / n;
    }

    public static String getFullNames() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                sb.append(employees[i].getFullName() + "\n");
            }
        }
        return sb.toString();
    }

    // повышенная сложность
    public static void indexSalaries(float percent) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                employees[i].indexSalary(percent);
            }
        }
    }

    // Поиск сотрудника с минимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public static Employee findMinSalaryForDepartment(int dep) {
        float min = employees[0].getSalary();
        int idx = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment()==dep && min > employees[i].getSalary()) {
                min = employees[i].getSalary();
                idx = i;
            }
        }
        if (idx == -1){
            return null; // нет сотрудников в данном отделе
        } else{
            return employees[idx];
        }
    }

    // Поиск сотрудника с максимальной ЗП по отделу (int dep). Возвращает null, если нет сотрудников
    public static Employee findMaxSalaryForDepartment(int dep) {
        float max = employees[0].getSalary();
        int idx = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment()==dep && max < employees[i].getSalary()) {
                max = employees[i].getSalary();
                idx = i;
            }
        }
        if (idx == -1){
            return null; // нет сотрудников в данном отделе
        } else{
            return employees[idx];
        }
    }

    // Сумма затрат на ЗП по отделу
    public static float calcSumSalaryForDepartment(int dep) {
        float sum = 0f;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    // Сумма средней ЗП по отделу. Возвращает Ср сумму ЗП или -1, если в отделе нет сотрудников
    public static float calcAverageSalaryForDepartment(int dep) {
        float sum = 0f;
        int n = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sum += employee.getSalary();
                n++;
            }
        }

        if (n > 0) {
            return sum / n;
        } else{
            return(-1);
        }
    }

    // Проиндесировать ЗП сотрудников на % = percent отдела dep
    public static void indexSalaries(float percent, int dep) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null && employees[i].getDepartment() == dep){
                employees[i].indexSalary(percent);
            }
        }
    }

    // Получить данные по всем сотрудникам отдела dep, не включая номер отдела
    public static String getEmployeeListForDepartment(int dep) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == dep) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }

        return sb.toString();
    }

    public static String getListSalaryLess(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }

    public static String getListSalaryOver(float salaryLimit) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() >= salaryLimit) {
                sb.append(employee.getDataWithoutDepartment() + "\n");
            }
        }
        return sb.toString();
    }
}