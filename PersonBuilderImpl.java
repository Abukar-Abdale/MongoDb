public class PersonBuilderImpl implements PersonBuilder {
    private String name;
    private int age;
    private String address;

    public PersonBuilderImpl() {}

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person buildPerson() {
        return new Person(name, age, address);
    }

    public Customer buildCustomer(int customerNumber) {
        return new Customer(name, age, address, customerNumber);
    }

    public Employee buildEmployee(int employeeNumber) {
        return new Employee(name, age, address, employeeNumber);
    }
}
