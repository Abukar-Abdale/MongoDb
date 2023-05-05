public class Employee {
    private String name;
    private int age;
    private String address;
    private int employeeNumber;

    public Employee(String name, int age, String address, int employeeNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.employeeNumber = employeeNumber;
    }

    public Employee(Person person, int employeeNumber) {
        this.name = person.getName();
        this.age = person.getAge();
        this.address = person.getAddress();
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}