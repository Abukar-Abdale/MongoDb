public interface CustomerBuilder {
    CustomerBuilder setCustomerNumber(int customerNumber);
    Customer buildCustomer(Person person);
}
