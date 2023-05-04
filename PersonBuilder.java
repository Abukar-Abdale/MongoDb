public interface PersonBuilder {
    PersonBuilder setName(String name);
    PersonBuilder setAge(int age);
    PersonBuilder setAddress(String address);
    Person buildPerson();
    Customer buildCustomer(int customerNumber);
    Employee buildEmployee(int employeeNumber);
}
