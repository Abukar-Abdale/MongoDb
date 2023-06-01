import org.bson.Document;

public class Employee extends Person {
    private final int employeeNumber;

    public Employee(String name, int age, String address, int employeeNumber) {
        super(name, age, address);
        this.employeeNumber = employeeNumber;
    }



    public int getEmployeeNumber() {
        return employeeNumber;
    }


    @Override
    public Document toDoc() {
        return super.toDoc().append("employee_number", employeeNumber);
    }
}