public class Employee extends Person {
    private int employeeNumber;

    public Employee(String name, int age, String address, int employeeNumber) {
        super(name, age, address);
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }
}
