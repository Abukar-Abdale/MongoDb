public class PersonFacade {
    private PersonBuilder personBuilder;

    public PersonFacade() {
        this.personBuilder = new PersonBuilderImpl();
    }

    public PersonFacade setName(String name) {
        personBuilder.setName(name);
        return this;
    }

    public PersonFacade setAge(int age) {
        personBuilder.setAge(age);
        return this;
    }

    public PersonFacade setAddress(String address) {
        personBuilder.setAddress(address);
        return this;
    }

    public Employee createEmployee(int employeeNumber) {
        Person person = personBuilder.buildPerson();
        EmployeeBuilder employeeBuilder = new EmployeeBuilderImpl();
        return employeeBuilder.setEmployeeNumber(employeeNumber).buildEmployee(person);
    }

    public Customer createCustomer(int customerNumber) {
        Person person = personBuilder.buildPerson();
        CustomerBuilder customerBuilder = new CustomerBuilderImpl();
        return customerBuilder.setCustomerNumber(customerNumber).buildCustomer(person);
    }
}
